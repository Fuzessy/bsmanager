package hu.fuz.bs.shopping.service;

import hu.fuz.bs.common.controller.ControllerUtility;
import hu.fuz.bs.common.dao.specification.SpecificationBuilder;
import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.service.event.EventType;
import hu.fuz.bs.shopping.service.event.ShoppingEventMonitor;
import hu.fuz.bs.shopping.dao.*;
import hu.fuz.bs.shopping.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class ShoppingService {
  @Autowired private ShoppingListItemRepository shoppingListItemRepository;
  @Autowired private ItemCategoryRepository itemCategoryRepository;
  @Autowired private ItemTargetRepository itemTargetRepository;

  @Resource private ShoppingEventMonitor shoppingEventMonitor;

  public ShoppingListItem createShoppingListItem(ShoppingListItem shoppingListItem, ApplicationUser user) {
    shoppingListItem.setRecordUser(user);
    shoppingListItem.setItemStatus(ItemStatus.CREATED);
    shoppingListItemRepository.save(shoppingListItem);
    shoppingEventMonitor.shoppingListItemCreated(user,shoppingListItem);
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

    EventType eventType = EventType.DATA_CHANGED;
    if(shoppingListItem.getItemStatus() != null && !itemFromDb.getItemStatus().equals(shoppingListItem.getItemStatus())){
      eventType = itemFromDb.modifyStatusTo(shoppingListItem.getItemStatus(), user);
    }
    itemFromDb.setQuantity(shoppingListItem.getQuantity());
    itemFromDb.setName(shoppingListItem.getName());
    itemFromDb.setNote(shoppingListItem.getNote());

    ShoppingListItem savedItem = shoppingListItemRepository.save(shoppingListItem);
    // ne akassza meg a többi tag értesítése a jelenlegi működést
    try{
      shoppingEventMonitor.shoppingListItemModified(user,eventType,savedItem);
    }catch (Exception e){
      e.printStackTrace();
    }
    return savedItem;
  }

  public List<ItemCategory> getItemCategories(boolean onlyAlive) {
    return itemCategoryRepository.findAll(
      SpecificationBuilder.where(onlyAlive, ShoppingCategorySpecifications.itemCategoryAlive()).build());
  }

  public Iterable<ItemTarget> getItemTargets() {
    return itemTargetRepository.findAll();
  }
}
