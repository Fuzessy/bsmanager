package hu.fuz.bs.shopping.dao;

import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.shopping.model.ItemCategory;
import hu.fuz.bs.shopping.model.ItemCategory_;
import org.springframework.data.jpa.domain.Specification;

public class ShoppingCategorySpecifications {
  public static Specification<ItemCategory> itemCategoryAlive() {
    return (root, query, builder) -> builder.isTrue(root.get(ItemCategory_.ALIVE));
  }
}
