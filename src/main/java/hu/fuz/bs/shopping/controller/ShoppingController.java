package hu.fuz.bs.shopping.controller;

import hu.fuz.bs.core.dao.ApplicationUserRepository;
import hu.fuz.bs.shopping.service.ShoppingService;
import hu.fuz.bs.shopping.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

  @Autowired private ShoppingService shoppingService;
  @Autowired private ApplicationUserRepository applicationUserRepository;

  @PostMapping("/shopping-list-item")
  public ShoppingListItem createShoppingListItem(@RequestBody ShoppingListItem shoppingListItem, Principal user){
    return shoppingService.createShoppingListItem(
      shoppingListItem,
      applicationUserRepository.findByUserName(user.getName()).get());
  }

  @GetMapping("/shopping-list-item")
  public List<ShoppingListItem> getShoppingListItems(
    @RequestParam(value = "priority", required = false) Priority priority,
    @RequestParam(value = "item-status", required = false) ItemStatus itemStatus,
    @RequestParam(value = "item-category", required = false) ItemCategory category,
    @RequestParam(value = "item-category", required = false) String order){
    return shoppingService.getShoppingListItems(priority,itemStatus,category,order);
  }

  @PutMapping("/shopping-list-item")
  public ShoppingListItem updateShoppingListItem(@RequestBody ShoppingListItem shoppingListItem, Principal user){
    return
      shoppingService.updateShoppingListItem(
        shoppingListItem,
        applicationUserRepository.findByUserName(user.getName()).get());
  }

  @GetMapping("/item-category")
  public  List<ItemCategory> getItemCategories(@RequestParam(required = false, name = "alive") boolean onlyAlive){
    return shoppingService.getItemCategories(onlyAlive);
  }

  @GetMapping("/priority")
  public Priority[] getPriorities(){
    return Priority.values();
  }


  @GetMapping("/item-target")
  public Iterable<ItemTarget> getItemTargets(){
    return shoppingService.getItemTargets();
  }


}
