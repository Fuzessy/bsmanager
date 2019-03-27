package hu.fuz.bs.test;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.finance.dao.AccountRepository;
import hu.fuz.bs.finance.dao.FinanceItemCategoryRepository;
import hu.fuz.bs.finance.dao.financeitem.FinanceItemRepository;
import hu.fuz.bs.finance.model.Account;
import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinancialItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Component
public class Initializer {

    @Autowired private FinanceItemCategoryRepository financeItemCategoryRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private FinanceItemRepository financeItemRepository;
    @Autowired private ApplicationUserRepository applicationUserRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public void init(){
        applicationUserRepository.save(ApplicationUser.builder()
          .id(1L).nickName("Zsolti").fullName("Füzesi Zsolt").password(passwordEncoder.encode("jelszó")).userName("zsolti").build());

        applicationUserRepository.save(ApplicationUser.builder()
          .id(2L).nickName("Barbi").fullName("Ecseri barbara").password(passwordEncoder.encode("jelszó")).userName("barbi").build());

        financeItemCategoryRepository.save( new FinancialItemCategory(1L,"étel"));
        financeItemCategoryRepository.save( new FinancialItemCategory(2L,"gyógyszer"));
        financeItemCategoryRepository.save( new FinancialItemCategory(3L,"benzin"));
        financeItemCategoryRepository.save( new FinancialItemCategory(4L,"Zsolti család"));
        financeItemCategoryRepository.save( new FinancialItemCategory(5L,"Barbi család"));

        accountRepository.save(new Account(1L,"OTP számla",1));
        accountRepository.save(new Account(2L,"Takarék számla",2));
        accountRepository.save(new Account(3L,"Zsolti kp",3));
        accountRepository.save(new Account(4L,"Barbi kp",4));
        accountRepository.save(new Account(5L,"Kp megtakarítás",5));

        FinanceItem financeItem = new FinanceItem();
        financeItem.setAmount(new BigDecimal("-10"));
        financeItem.setBalance(new BigDecimal("-10"));
        financeItem.setOrderNumber(0);
        financeItem.setCategory(financeItemCategoryRepository.findById(1L).get());
        financeItem.setTargetAccount(accountRepository.findAccountByOrderNumber(1).get());
        financeItem.setTransactionDate(LocalDate.now().minusDays(10));
        financeItem.setRecordTimestamp(new Date());
        financeItem.setRecordUser(applicationUserRepository.findById(1L).get());
        financeItemRepository.save(financeItem);

        financeItem = new FinanceItem();
        financeItem.setAmount(new BigDecimal("100"));
        financeItem.setBalance(new BigDecimal("90"));
        financeItem.setOrderNumber(1);
        financeItem.setCategory(financeItemCategoryRepository.findById(1L).get());
        financeItem.setTargetAccount(accountRepository.findAccountByOrderNumber(1).get());
        financeItem.setTransactionDate(LocalDate.now().minusDays(1));
        financeItem.setRecordTimestamp(new Date());
      financeItem.setRecordUser(applicationUserRepository.findById(1L).get());
        financeItemRepository.save(financeItem);

        financeItem = new FinanceItem();
        financeItem.setAmount(new BigDecimal("10"));
        financeItem.setBalance(new BigDecimal("100"));
        financeItem.setOrderNumber(2);
        financeItem.setCategory(financeItemCategoryRepository.findById(1L).get());
        financeItem.setTargetAccount(accountRepository.findAccountByOrderNumber(1).get());
        financeItem.setTransactionDate(LocalDate.now());
        financeItem.setRecordTimestamp(new Date());
      financeItem.setRecordUser(applicationUserRepository.findById(2L).get());
        financeItemRepository.save(financeItem);


    }
}
