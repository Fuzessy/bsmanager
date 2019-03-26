package hu.fuz.bs.finance.controller;

import hu.fuz.bs.common.controller.ControllerUtility;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static hu.fuz.bs.finance.dao.financeitem.CustomerSpecifications.*;
import static org.hibernate.criterion.Restrictions.and;

@Controller
public class FinancialController {

    @Autowired private AccountRepository accountRepository;
    @Autowired private FinanceItemCategoryRepository financeItemCategoryRepository;
    @Autowired private FinanceItemRepository financeItemRepository;
    @Autowired private TransactionHandler transactionHandler;

    public Iterable<Account> getUserAccounts() {
        return accountRepository.findAll();
    }

    public Iterable<FinanceItem> getFinancialItems(Optional<LocalDate> transactionDateFrom,
                                                   Optional<LocalDate> transactionDateTo, int accountId) {
      List<Specification> filters = new ArrayList<>();

      filters.add(accountIdIs(accountId));
      transactionDateTo.ifPresent(localDate -> filters.add(lessThanEq(localDate)));
      transactionDateFrom.ifPresent(localDate -> filters.add(greaterThanEq(localDate)));

      return financeItemRepository.findAll((a, b, c) ->  c.and(),
              Sort.by(Sort.Order.desc(FinanceItem_.orderNumber.getName())));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void createFinancialItem(FinanceItem financeItem) {
           financeItem.setId(null);
        financeItem.setRecordTimestamp(new Date());
        financeItem.setBalance(BigDecimal.ZERO);

        transactionHandler.setBalanceAndOrderByLastFinanceItem(financeItem);
        financeItemRepository.save(financeItem);
        transactionHandler.updateFinanceItemBalanceAnfOrderNumber();
    }

    public Iterable<FinancialItemCategory> getFinancialItemCategories() {
        return financeItemCategoryRepository.findAll();
    }

    public void updateFinancialItem(FinanceItem financeItem) {
        FinanceItem toUpdate = ControllerUtility.getOrNotFound(
                financeItemRepository.findById(financeItem.getId()),
                "Transaction not found!");

        toUpdate.setTargetAccount(
                ControllerUtility.getOrNotFound(accountRepository.findById(financeItem.getTargetAccount().getId()),
                        "Target account not found!"));

        toUpdate.setCategory(
                ControllerUtility.getOrNotFound(
                financeItemCategoryRepository.findById(financeItem.getCategory().getId()),"Transaction category not found!"));

        toUpdate.setNote(financeItem.getNote());

        // ha a dátum módosult, helyezzük át
        // ekkor az összeget is kezeljük
        if(!financeItem.getTransactionDate().equals(toUpdate.getTransactionDate())){
            toUpdate.setTransactionDate(financeItem.getTransactionDate());
            toUpdate.setAmount(financeItem.getAmount());
            toUpdate.setOrderNumber(Integer.MAX_VALUE);
            //transactionHandler.setBalanceAndOrderByLastFinanceItem(toUpdate);
            financeItemRepository.save(toUpdate);
            transactionHandler.updateFinanceItemBalanceAnfOrderNumber();
        }else if(financeItem.getAmount().compareTo(toUpdate.getAmount()) != 0){
            // ha csak az összeg, a következő elemeket számoljuk újra
            toUpdate.setAmount(financeItem.getAmount());
            financeItemRepository.save(toUpdate);
            transactionHandler.updateFinanceItemBalanceAnfOrderNumber();
        }

        //updateFinanceItems(toUpdate, toUpdate.getOrderNumber());

    }

    public List<FinanceItem> moveBackFinancialItem(long financeItemId) {
        FinanceItem financeItemFirst =
            financeItemRepository.findById(financeItemId).orElseThrow(
                () -> new BSEntityNotFoundException("Tarnzakció nem található! Id: " + financeItemId));
        FinanceItem financeItemSecond =
            financeItemRepository.getFinanceItemByOrderNumber(financeItemFirst.getOrderNumber() - 1).orElseThrow(
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
                financeItemRepository.getFinanceItemByOrderNumber(financeItemSecond.getOrderNumber() + 1).orElseThrow(
                        () -> new FinancialExceptions("Nem lehet a tranzakciót előresorolni, mivel nincs rákövetkező tranzakció!"));

        transactionHandler.changeTransactionOrder(financeItemFirst, financeItemSecond);
        financeItemRepository.saveAll(Arrays.asList(financeItemFirst, financeItemSecond));
        return Arrays.asList(financeItemFirst, financeItemSecond);
    }
}
