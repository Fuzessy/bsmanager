package hu.fuz.bs.finance.controller;

import hu.fuz.bs.common.controller.ControllerUtility;
import hu.fuz.bs.common.dao.specification.SpecificationBuilder;
import hu.fuz.bs.common.exceptions.BSEntityNotFoundException;
import hu.fuz.bs.finance.dao.AccountRepository;
import hu.fuz.bs.finance.dao.FinanceItemCategoryRepository;
import hu.fuz.bs.finance.dao.financeitem.FinanceItemRepository;
import hu.fuz.bs.finance.exceptions.FinancialExceptions;
import hu.fuz.bs.finance.model.Account;
import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinanceItem_;
import hu.fuz.bs.finance.model.FinancialItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static hu.fuz.bs.finance.dao.financeitem.CustomerSpecifications.*;
import static org.hibernate.criterion.Restrictions.and;

@Controller
public class FinancialController {

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private FinanceItemCategoryRepository financeItemCategoryRepository;
  @Autowired
  private FinanceItemRepository financeItemRepository;
  @Autowired
  private TransactionHandler transactionHandler;

  public Iterable<Account> getUserAccounts() {
    return accountRepository.findAll();
  }

  public Iterable<FinanceItem> getFinancialItems(Optional<LocalDate> transactionDateFrom,
                                                 Optional<LocalDate> transactionDateTo, int accountId) {
    return financeItemRepository.findAll(
      SpecificationBuilder
        .where(accountIdIs(accountId))
        .and(transactionDateTo.isPresent(),() -> lessThanEq(transactionDateTo.get()))
        .and(transactionDateFrom.isPresent(),() -> greaterThanEq(transactionDateFrom.get())).build(),
      Sort.by(Sort.Order.desc(FinanceItem_.orderNumber.getName())));
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void createFinancialItem(FinanceItem financeItem) {
    if (financeItem.getTargetAccount() != null
      && financeItem.getSourceAccount() != null
      && financeItem.getTargetAccount().getId().equals(financeItem.getSourceAccount().getId())) {
      throw new FinancialExceptions("A forrás- és célszámla nem lehet ugyanaz!");
    }

    transactionHandler.createSingleFinanceItem(financeItem);

    if (financeItem.getTargetAccount() != null) {
      transactionHandler.createFinanceItemForTargetAccount(financeItem);
    }
  }

  public Iterable<FinancialItemCategory> getFinancialItemCategories() {
    return financeItemCategoryRepository.findAll();
  }

  public void updateFinancialItem(FinanceItem financeItem) {
    FinanceItem toUpdate = ControllerUtility.getOrNotFound(
      financeItemRepository.findById(financeItem.getId()),
      "Transaction not found!");

    if (toUpdate.getTargetAccount() != null || financeItem.getTargetAccount() != null) {
      if (Account.equalsItems(toUpdate.getSourceAccount(), financeItem.getSourceAccount())) {
        throw new FinancialExceptions("Számlák közötti átvezetés esetén a forrásszámla módosítását még nem implementáltam!");
      }
      if (Account.equalsItems(toUpdate.getTargetAccount(), financeItem.getTargetAccount())) {
        throw new FinancialExceptions("Számlák közötti átvezetés esetén a célszámla módosítását még nem implementáltam!");
      }
    }

    if(!toUpdate.getSourceAccount().getId().equals(financeItem.getSourceAccount().getId())){
      toUpdate.setSourceAccount(
        ControllerUtility.getOrNotFound(accountRepository.findById(financeItem.getSourceAccount().getId()),
          "Account not found!"));
      // módosítsuk azt, ahonnan átraktuk a tranzakciót
      transactionHandler.updateFinanceItemBalanceAnfOrderNumber(financeItem.getSourceAccount().getId());
    }

    toUpdate.setCategory(
      ControllerUtility.getOrNotFound(
        financeItemCategoryRepository.findById(financeItem.getCategory().getId()), "Transaction category not found!"));

    toUpdate.setNote(financeItem.getNote());

    // ha a dátum módosult, helyezzük át
    // ekkor az összeget is kezeljük

    toUpdate.setTransactionDate(financeItem.getTransactionDate());
    toUpdate.setAmount(financeItem.getAmount());
    financeItemRepository.save(toUpdate);
    transactionHandler.updateFinanceItemBalanceAnfOrderNumber(toUpdate.getSourceAccount().getId());




//    if (!financeItem.getTransactionDate().equals(toUpdate.getTransactionDate())) {
//      toUpdate.setTransactionDate(financeItem.getTransactionDate());
//      toUpdate.setAmount(financeItem.getAmount());
//      toUpdate.setOrderNumber(Integer.MAX_VALUE);
//      //transactionHandler.setBalanceAndOrderByLastFinanceItem(toUpdate);
//      financeItemRepository.save(toUpdate);
//      transactionHandler.updateFinanceItemBalanceAnfOrderNumber(toUpdate.getSourceAccount().getId());
//    } else if (financeItem.getAmount().compareTo(toUpdate.getAmount()) != 0) {
//      // ha csak az összeg, a következő elemeket számoljuk újra
//      toUpdate.setAmount(financeItem.getAmount());
//      financeItemRepository.save(toUpdate);
//      transactionHandler.updateFinanceItemBalanceAnfOrderNumber(toUpdate.getSourceAccount().getId());
//    }

    //updateFinanceItems(toUpdate, toUpdate.getOrderNumber());

  }

  public List<FinanceItem> moveBackFinancialItem(long financeItemId) {
    FinanceItem financeItemFirst =
      financeItemRepository.findById(financeItemId).orElseThrow(
        () -> new BSEntityNotFoundException("Tarnzakció nem található! Id: " + financeItemId));
    FinanceItem financeItemSecond =
      financeItemRepository.getFinanceItemByOrderNumberAndSourceAccount(
        financeItemFirst.getOrderNumber() - 1, financeItemFirst.getSourceAccount()).orElseThrow(
        () -> new FinancialExceptions("Nem lehet a tranzakciót hátrasorolni, mivel nincs megelőző tranzakció!"));

    transactionHandler.changeTransactionOrder(financeItemFirst, financeItemSecond);
    financeItemRepository.saveAll(Arrays.asList(financeItemFirst, financeItemSecond));
    return Arrays.asList(financeItemFirst, financeItemSecond);
  }

  public List<FinanceItem> moveAheadFinancialItem(long financeItemId) {
    FinanceItem financeItemSecond =
      financeItemRepository.findById(financeItemId).orElseThrow(
        () -> new BSEntityNotFoundException("Tarnzakció nem található! Id: " + financeItemId));
    FinanceItem financeItemFirst =
      financeItemRepository.getFinanceItemByOrderNumberAndSourceAccount(
        financeItemSecond.getOrderNumber() + 1, financeItemSecond.getSourceAccount()).orElseThrow(
        () -> new FinancialExceptions("Nem lehet a tranzakciót előresorolni, mivel nincs rákövetkező tranzakció!"));

    transactionHandler.changeTransactionOrder(financeItemFirst, financeItemSecond);
    financeItemRepository.saveAll(Arrays.asList(financeItemFirst, financeItemSecond));
    return Arrays.asList(financeItemFirst, financeItemSecond);
  }

  public FinancialItemCategory passivateFinancialItemCategory(FinancialItemCategory financialItemCategory) {
    FinancialItemCategory fromStore = financeItemCategoryRepository.findById(financialItemCategory.getId())
      .orElseThrow( () -> new BSEntityNotFoundException("Kategória nem található!"));
    if(!fromStore.isAlive()){
      throw new FinancialExceptions(fromStore.getName() + " már passzív!");
    }
    fromStore.setAlive(false);
    financeItemCategoryRepository.save(fromStore);
    return fromStore;
  }

  public FinancialItemCategory activateFinancialItemCategory(FinancialItemCategory financialItemCategory) {
    FinancialItemCategory fromStore = financeItemCategoryRepository.findById(financialItemCategory.getId())
      .orElseThrow( () -> new BSEntityNotFoundException("Kategória nem található!"));
    if(fromStore.isAlive()){
      throw new FinancialExceptions(fromStore.getName() + " már aktív!");
    }
    fromStore.setAlive(true);
    financeItemCategoryRepository.save(fromStore);
    return fromStore;
  }

  public FinancialItemCategory updateFinancialItemCategory(FinancialItemCategory financialItemCategory) {
    FinancialItemCategory fromStore = financeItemCategoryRepository.findById(financialItemCategory.getId())
      .orElseThrow( () -> new BSEntityNotFoundException("Kategória nem található!"));
    fromStore.setName(financialItemCategory.getName());
    financeItemCategoryRepository.save(fromStore);
    return fromStore;
  }
}
