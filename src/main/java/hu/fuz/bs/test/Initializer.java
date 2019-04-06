package hu.fuz.bs.test;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.finance.dao.AccountRepository;
import hu.fuz.bs.finance.dao.FinanceItemCategoryRepository;
import hu.fuz.bs.finance.dao.financeitem.FinanceItemRepository;
import hu.fuz.bs.finance.model.Account;
import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinancialItemCategory;
import hu.fuz.bs.shopping.dao.ItemCategoryRepository;
import hu.fuz.bs.shopping.dao.ItemTargetRepository;
import hu.fuz.bs.shopping.dao.ShoppingListItemRepository;
import hu.fuz.bs.shopping.model.ItemCategory;
import hu.fuz.bs.shopping.model.ItemStatus;
import hu.fuz.bs.shopping.model.ItemTarget;
import hu.fuz.bs.shopping.model.ShoppingListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class Initializer {

    @Autowired private FinanceItemCategoryRepository financeItemCategoryRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private FinanceItemRepository financeItemRepository;
    @Autowired private ApplicationUserRepository applicationUserRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Autowired private ShoppingListItemRepository shoppingListItemRepository;
    @Autowired private ItemCategoryRepository itemCategoryRepository;
    @Autowired private ItemTargetRepository itemTargetRepository;

    public void init(){
      initUserData();
      initFinanceModulData();
      initShoppingModuleData();
    }

  private void initShoppingModuleData() {
      itemTargetRepository.save(ItemTarget.builder().id(1L).name("Saját").build());
      itemTargetRepository.save(ItemTarget.builder().id(2L).name("Ica mama").build());
      itemTargetRepository.save(ItemTarget.builder().id(3L).name("Szandra").build());
      itemTargetRepository.save(ItemTarget.builder().id(4L).name("Anyuci").build());
      itemTargetRepository.save(ItemTarget.builder().id(5L).name("Pécel").build());
      itemTargetRepository.save(ItemTarget.builder().id(6L).name("Egyéb").build());

      itemCategoryRepository.save(ItemCategory.builder().id(1L).name("Pékáru").build());
      itemCategoryRepository.save(ItemCategory.builder().id(2L).name("Tejtermék").build());
      itemCategoryRepository.save(ItemCategory.builder().id(3L).name("Tisztítószer").build());
      itemCategoryRepository.save(ItemCategory.builder().id(4L).name("Egyéb").build());

      shoppingListItemRepository.save(ShoppingListItem.builder().id(1L)
        .category(itemCategoryRepository.findById(1L).get())
        .recordTime(LocalDateTime.now())
        .recordUser(applicationUserRepository.findById(1L).get())
        .name("kenyér")
        .itemStatus(ItemStatus.CREATED)
        .quantity("10 db").build());
  }

  private void initUserData(){
      applicationUserRepository.save(ApplicationUser.builder()
        .id(1L).nickName("Zsolti").fullName("Füzesi Zsolt").password(passwordEncoder.encode("jelszó")).userName("zsolti").build());

      applicationUserRepository.save(ApplicationUser.builder()
        .id(2L).nickName("Barbi").fullName("Ecseri barbara").password(passwordEncoder.encode("jelszó")).userName("barbi").build());

    }

    private void initFinanceModulData(){
      financeItemCategoryRepository.save( FinancialItemCategory.builder().id(1L).name("étel").build());
      financeItemCategoryRepository.save( FinancialItemCategory.builder().id(2L).name("gyógyszer").build());
      financeItemCategoryRepository.save( FinancialItemCategory.builder().id(3L).name("benzin").build());
      financeItemCategoryRepository.save( FinancialItemCategory.builder().id(4L).name("Zsolti család").build());
      financeItemCategoryRepository.save( FinancialItemCategory.builder().id(5L).name("Barbi család").build());

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
      financeItem.setSourceAccount(accountRepository.findAccountByOrderNumber(1).get());
      financeItem.setTransactionDate(LocalDate.now().minusDays(10));
      financeItem.setRecordTimestamp(new Date());
      financeItem.setRecordUser(applicationUserRepository.findById(1L).get());
      financeItemRepository.save(financeItem);

      financeItem = new FinanceItem();
      financeItem.setAmount(new BigDecimal("100"));
      financeItem.setBalance(new BigDecimal("90"));
      financeItem.setOrderNumber(1);
      financeItem.setCategory(financeItemCategoryRepository.findById(1L).get());
      financeItem.setSourceAccount(accountRepository.findAccountByOrderNumber(1).get());
      financeItem.setTransactionDate(LocalDate.now().minusDays(1));
      financeItem.setRecordTimestamp(new Date());
      financeItem.setRecordUser(applicationUserRepository.findById(1L).get());
      financeItemRepository.save(financeItem);

      financeItem = new FinanceItem();
      financeItem.setAmount(new BigDecimal("10"));
      financeItem.setBalance(new BigDecimal("100"));
      financeItem.setOrderNumber(2);
      financeItem.setCategory(financeItemCategoryRepository.findById(1L).get());
      financeItem.setSourceAccount(accountRepository.findAccountByOrderNumber(1).get());
      financeItem.setTransactionDate(LocalDate.now());
      financeItem.setRecordTimestamp(new Date());
      financeItem.setRecordUser(applicationUserRepository.findById(2L).get());
      financeItemRepository.save(financeItem);

    }
}
