package hu.fuz.bs.shopping.service.event;

import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.model.ShoppingListItem;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class ShoppingEventMonitor {

  private static final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());

  public SseEmitter addEmitter(){
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);
    emitter.onCompletion(()->emitters.remove(emitter));
    return emitter;
  }

  public void shoppingListItemCreated(ApplicationUser user, ShoppingListItem item){
    notify(user,EventType.CREATE,item);
  }

  public void shoppingListItemModified(ApplicationUser user, EventType eventType, ShoppingListItem item) {
    this.notify(user,eventType, item);
  }

  private void notify(ApplicationUser user, EventType eventType, ShoppingListItem item) {
    ShoppingEvent event = ShoppingEvent.builder()
      .user(user)
      .eventType(eventType)
      .item(item).build();

    Iterator<SseEmitter> iterator = emitters.iterator();

    while(iterator.hasNext()){
      SseEmitter emitter = iterator.next();
      try {
        emitter.send(event,MediaType.APPLICATION_JSON);
      } catch (Exception e) {
        iterator.remove();
      }
    }

  }


}
