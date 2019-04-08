package hu.fuz.bs.shopping.controller;

import hu.fuz.bs.common.controller.ControllerUtility;
import hu.fuz.bs.common.dao.specification.SpecificationBuilder;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.dao.*;
import hu.fuz.bs.shopping.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Configuration
public class ShoppingController {
  @Autowired private ShoppingListItemRepository shoppingListItemRepository;
  @Autowired private ItemCategoryRepository itemCategoryRepository;
  @Autowired private ItemTargetRepository itemTargetRepository;

  public ShoppingListItem createShoppingListItem(ShoppingListItem shoppingListItem, ApplicationUser applicationUser) {
    shoppingListItem.setRecordUser(applicationUser);
    shoppingListItem.setItemStatus(ItemStatus.CREATED);
    shoppingListItemRepository.save(shoppingListItem);
    return shoppingListItem;
  }

  public List<ShoppingListItem> getShoppingListItems(Priority priority, ItemStatus itemStatus, ItemCategory category, String order) {

    Specification<ShoppingListItem> spec =
      SpecificationBuilder
      .where(priority != null,ShoppingListItemSpecifications.priorityEquals(priority))
      .and(itemStatus != null,ShoppingListItemSpecifications.itesmStatusEquals(itemStatus))
      .and(category != null,ShoppingListItemSpecifications.categoryEquals(category))
      .build();

    return shoppingListItemRepository.findAll(spec,
      order != null ? Sort.by(Sort.Order.desc(order)) : Sort.by(Sort.Order.desc(ShoppingListItem_.PRIORITY)));
  }

  public ShoppingListItem updateShoppingListItem(ShoppingListItem shoppingListItem, ApplicationUser user) {
    ShoppingListItem itemFromDb = ControllerUtility.getOrNotFound(
      shoppingListItemRepository.findById(shoppingListItem.getId()), "Shopping list item not found!");

    if(shoppingListItem.getItemStatus() != null && !itemFromDb.getItemStatus().equals(shoppingListItem.getItemStatus())){
      itemFromDb.modifyStatusTo(shoppingListItem.getItemStatus(), user);
    }
    itemFromDb.setQuantity(shoppingListItem.getQuantity());
    itemFromDb.setName(shoppingListItem.getName());
    itemFromDb.setNote(shoppingListItem.getNote());

    shoppingListItemRepository.save(shoppingListItem);
    return null;
  }

  public List<ItemCategory> getItemCategories(boolean onlyAlive) {
    return itemCategoryRepository.findAll(
      SpecificationBuilder.where(onlyAlive, ShoppingCategorySpecifications.itemCategoryAlive()).build());
  }

  public Iterable<ItemTarget> getItemTargets() {
    return itemTargetRepository.findAll();
  }
}
