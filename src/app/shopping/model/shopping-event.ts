import {ApplicationUser} from "../../core/model/application-user";
import {ShoppingListItem} from "./shopping-list-item";

export interface ShoppingEvent {
  eventType : string;
  user : ApplicationUser ;
  item : ShoppingListItem;
}
