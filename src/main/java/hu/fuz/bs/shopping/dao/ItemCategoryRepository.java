package hu.fuz.bs.shopping.dao;

import hu.fuz.bs.shopping.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ItemCategoryRepository extends CrudRepository<ItemCategory,Long> , JpaSpecificationExecutor<ItemCategory> {
}
