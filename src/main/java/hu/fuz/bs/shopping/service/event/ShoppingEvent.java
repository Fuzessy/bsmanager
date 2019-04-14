package hu.fuz.bs.shopping.service.event;

import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.model.ShoppingListItem;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ShoppingEvent {
  private EventType eventType;
  private ApplicationUser user;
  private ShoppingListItem item;
}
