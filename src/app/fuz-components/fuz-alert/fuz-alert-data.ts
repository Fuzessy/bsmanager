import {ButtonOptions} from "./button-optins";
import {FuzAlertCloseEvents} from "./fuz-alert-close-events";

export interface FuzAlertData{
  title : string;
  message : string;
  details? : string;
  buttonOption? : ButtonOptions;
  fuzAlertCloseEvents : FuzAlertCloseEvents;
}
