package hu.fuz.bs.shopping.dao;

import hu.fuz.bs.shopping.model.*;
import org.springframework.data.jpa.domain.Specification;

public class ShoppingListItemSpecifications {

  public static Specification<ShoppingListItem> priorityEquals(Priority priority){
    return (root, query, builder) -> builder.equal(root.get(ShoppingListItem_.priority), priority);
  }

  public static Specification<ShoppingListItem> itesmStatusEquals(ItemStatus itemStatus){
    return (root, query, builder) -> builder.equal(root.get(ShoppingListItem_.itemStatus), itemStatus);
  }


  public static Specification<ShoppingListItem> categoryEquals(ItemCategory category){
    return (root, query, builder) -> builder.equal(root.get(ShoppingListItem_.category), category);
  }
}
