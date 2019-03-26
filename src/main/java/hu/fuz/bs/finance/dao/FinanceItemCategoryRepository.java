package hu.fuz.bs.finance.dao;

import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinancialItemCategory;
import org.springframework.data.repository.CrudRepository;

public interface FinanceItemCategoryRepository extends CrudRepository<FinancialItemCategory,Long> {
}
