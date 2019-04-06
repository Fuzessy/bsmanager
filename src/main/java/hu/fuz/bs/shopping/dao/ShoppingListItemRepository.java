package hu.fuz.bs.shopping.dao;

import hu.fuz.bs.shopping.model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ShoppingListItemRepository extends PagingAndSortingRepository<ShoppingListItem,Long>, JpaSpecificationExecutor<ShoppingListItem> {

}
