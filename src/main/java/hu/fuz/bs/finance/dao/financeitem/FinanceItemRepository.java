package hu.fuz.bs.finance.dao.financeitem;

import hu.fuz.bs.finance.model.Account;
import hu.fuz.bs.finance.model.FinanceItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface FinanceItemRepository extends CrudRepository<FinanceItem,Long>, JpaSpecificationExecutor<FinanceItem> {

    @Query("SELECT coalesce(max(f.orderNumber),-1) FROM FinanceItem f WHERE f.transactionDate <= :transactionDate" +
            " AND f.sourceAccount = :account ")
    int getMaxOrderNumberBefore(@Param("transactionDate") LocalDate transactionDate, @Param("account") Account account);

    Optional<FinanceItem> getFinanceItemByOrderNumberAndSourceAccount(int orderNumber, Account sourceAccount);

}
