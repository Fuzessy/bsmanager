export interface FuzAlertCloseEvents{
  yesEvent? : () => void;
  noEvent? : () => void;
  cancelEvent? : () => void;
  okEvent? : () => void;
  escapeEvent? : () => void;
}
