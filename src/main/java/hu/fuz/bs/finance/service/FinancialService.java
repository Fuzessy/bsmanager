package hu.fuz.bs.finance.service;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.finance.controller.FinancialController;
import hu.fuz.bs.finance.model.Account;
import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinancialItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/finance")
public class FinancialService {

  @Autowired
  private FinancialController financialController;

  @Autowired
  private ApplicationUserRepository applicationUserRepository;

  @PostMapping("/finance-item")
  public void createFinancialItem(@RequestBody FinanceItem financeItem, Principal user) {
    financeItem.setRecordUser(applicationUserRepository.findByUserName(user.getName()).get());
    financialController.createFinancialItem(financeItem);
  }

  /**
   * RequestParam required = false csak a LocalDate miatt szükséges, mert nincs konstruktora,
   * de az optional miatt ne legyen null
   *
   * @param transactionDateFrom
   * @param transactionDateTo
   * @param accountId
   * @return
   */
  @GetMapping("/finance-item")
  public Iterable<FinanceItem> getFinancialItems(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Optional<LocalDate> transactionDateFrom
    , @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Optional<LocalDate> transactionDateTo
    , int accountId) {
    return financialController.getFinancialItems(transactionDateFrom, transactionDateTo, accountId);
  }

  @PutMapping("/finance-item")
  public void updateFinancialItem(@RequestBody FinanceItem financeItem) {
    financialController.updateFinancialItem(financeItem);
  }

  @PutMapping("/moveback")
  public List<FinanceItem> moveBackFinancialItem(@RequestBody int financeItemId) {
    return financialController.moveBackFinancialItem(financeItemId);
  }

  @PutMapping("/moveahead")
  public List<FinanceItem> moveAheadFinancialItem(@RequestBody int financeItemId) {
    return financialController.moveAheadFinancialItem(financeItemId);
  }

  @GetMapping("/finance-item-categories")
  public Iterable<FinancialItemCategory> getFinancialItemCategories() {
    return financialController.getFinancialItemCategories();
  }

  @PutMapping("/finance-item-categories/operations/passivate")
  public FinancialItemCategory passivateFinancialItemCategory(@RequestBody FinancialItemCategory financialItemCategory) {
    return financialController.passivateFinancialItemCategory(financialItemCategory);
  }

  @PutMapping("/finance-item-categories/operations/activate")
  public FinancialItemCategory activateFinancialItemCategory(@RequestBody FinancialItemCategory financialItemCategory) {
    return financialController.activateFinancialItemCategory(financialItemCategory);
  }

  @PutMapping("/finance-item-categories")
  public FinancialItemCategory updateFinancialItemCategories(@RequestBody FinancialItemCategory financialItemCategory) {
    return financialController.updateFinancialItemCategory(financialItemCategory);
  }


  @GetMapping("/user-account")
  public Iterable<Account> getUserAccounts() {
    return financialController.getUserAccounts();
  }

}
