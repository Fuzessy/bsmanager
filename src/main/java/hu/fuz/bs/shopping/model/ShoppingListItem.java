package hu.fuz.bs.shopping.model;

import hu.fuz.bs.core.model.ApplicationUser;
import hu.fuz.bs.shopping.service.event.EventType;
import hu.fuz.bs.shopping.model.exceptions.ShoppingException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ShoppingListItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String quantity;
  private String note;

  @ManyToOne
  private ItemCategory category;

  @Enumerated(value = EnumType.STRING)
  private Priority priority;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private ItemStatus itemStatus;

  @ManyToOne(optional = false)
  private ApplicationUser recordUser;
  private LocalDateTime recordTime;

  @ManyToOne
  private ApplicationUser purchaseUser;
  private LocalDateTime purchaseTime;

  @ManyToOne
  private ApplicationUser deleteUser;
  private LocalDateTime deleteTime;

  @ManyToOne
  private ItemTarget target;

  public EventType modifyStatusTo(ItemStatus itemStatus, ApplicationUser user) {
    if(this.itemStatus == ItemStatus.CREATED){
      if(itemStatus == ItemStatus.DELETED){
        setDeleteStatus(user);
        return EventType.DELETE;
      }else if(itemStatus == ItemStatus.PURCHASED){
        setPurchasedStatus(user);
        return EventType.PURCHASE;
      }

    }else if(this.itemStatus == ItemStatus.PURCHASED){
      if(itemStatus == ItemStatus.CREATED){
        setCreatedStatusAgain();
        return EventType.PUT_BACK;
      }else if(itemStatus == ItemStatus.DELETED){
        throw new ShoppingException("Teljesült tétel nem törölhető!");
      }
    }else if(this.itemStatus == ItemStatus.DELETED){
      if(itemStatus == ItemStatus.CREATED){
        setCreatedStatusAgain();
        return EventType.RESTORE;
      }else if(itemStatus == ItemStatus.PURCHASED){
        throw new ShoppingException("Törölt tétel nem lehet teljesült!");
      }
    }
    return null;
  }

  private void setCreatedStatusAgain() {
    itemStatus = ItemStatus.CREATED;
    purchaseTime = null;
    deleteTime = null;
  }

  private void setPurchasedStatus(ApplicationUser user) {
    itemStatus = ItemStatus.PURCHASED;
    purchaseTime = LocalDateTime.now();
    purchaseUser = user;
  }

  private void setDeleteStatus(ApplicationUser user) {
    itemStatus = ItemStatus.DELETED;
    deleteTime = LocalDateTime.now();
    deleteUser = user;
  }

}
