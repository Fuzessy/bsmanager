package hu.fuz.bs.finance.dao.financeitem;

import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinanceItem_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CustomerSpecifications {

    public static Specification<FinanceItem> lessThanEq(LocalDate dateTo) {
        return  (root,  query, builder) ->
                builder.lessThanOrEqualTo(root.get(FinanceItem_.transactionDate),dateTo);
    }

    public static Specification<FinanceItem> greaterThanEq(LocalDate dateFrom) {
        return  (root,  query, builder) ->
                builder.greaterThanOrEqualTo(root.get(FinanceItem_.transactionDate),dateFrom);
    }

    public static Specification<FinanceItem> accountIdIs(long accountId) {
        return  (root,  query, builder) -> {
            return builder.equal(root.get(FinanceItem_.sourceAccount).get("Id"),accountId);
        };
    }

    public static Specification<FinanceItem> greaterThanEqOrderNumber(int dailyOrder) {
        return (root,  query, builder) ->
                builder.greaterThanOrEqualTo(root.get(FinanceItem_.orderNumber),dailyOrder);
    }
}
