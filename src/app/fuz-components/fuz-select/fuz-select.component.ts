import {Component, ElementRef, forwardRef, Input, OnInit, ViewChild} from '@angular/core';
import {
  AbstractControl,
  ControlValueAccessor,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR,
  ValidationErrors,
  Validator
} from "@angular/forms";
import {FuzNumberComponent} from "../fuz-number/fuz-number.component";

@Component({
  selector: 'fuz-select',
  templateUrl: './fuz-select.component.html',
  styleUrls: ['./fuz-select.component.css'],
  providers:[
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FuzSelectComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => FuzSelectComponent),
      multi: true
    }
  ],
})
export class FuzSelectComponent implements OnInit,ControlValueAccessor, Validator  {

  private origItems : any[];

  _selectedElement : any;
  set selectedElement(element: any){
    this._selectedElement = element;
    this.propagateChange(element);
  }
  get selectedElement():any{
    return this._selectedElement;
  }

  modelItems : {origItem,htmlValue,match}[] = [];

  @ViewChild("selectInput") inputField: ElementRef;
  itemContainerClass = "hideItemContainer";
  private hideTime : Date = new Date();

  constructor(private elRef: ElementRef) {
    this.origItems = [];
  }


  ngOnInit() {
    this.hideTime = new Date();
  }

  @Input() key: string;
  @Input() value: string;
  @Input() label: string;
  @Input() required: boolean;

  @Input()
  set items(items: any[]){
    this.origItems = items;
    this.setItemsFromOrig();
  }

  private setItemsFromOrig() {
    if(this.key !== undefined && this.value !== undefined && this.origItems !== undefined){
      this.modelItems = [];
      this.origItems.forEach(i => {
        //console.log(i[this.value]);
        this.modelItems.push({origItem: i, htmlValue: i[this.value], match:false})
      });
    }
  }

  get items() : any[]{
    return this.origItems;
  }

  private propagateChange = (_: any) => { };
  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }


  registerOnTouched(fn: any): void {
  }

  setDisabledState(isDisabled: boolean): void {
  }

  writeValue(obj: any): void {
    this.selectedElement = obj;
  }


  registerOnValidatorChange(fn: () => void): void {
  }

  public errors : {code:string, message:string} [] = [];
  validate(control: AbstractControl): ValidationErrors | null {
    this.errors = [];
    if(this.selectedElement === undefined || this.selectedElement === null){
      if(this.required){
        this.errors.push({
          code: 'REQUIRED', message: 'Kötelező mező!'
        });
      }
    }

    if(this.errors.length > 0){
      return this.errors;
    }
    return null;
  }


  showItems(mode : string){
    if(new Date().getTime() - this.hideTime.getTime() < 500){
      return;
    }
    if(mode == 'toggle'){
      if (this.itemContainerClass === "hideItemContainer") {
        this.itemContainerClass = "showItemContainer";
        this.inputField.nativeElement.focus();
        this.onSearchChange(null);
      } else {
        this.itemContainerClass = "hideItemContainer"
      }
    }else {
      if (this.itemContainerClass === "hideItemContainer") {
        this.itemContainerClass = "showItemContainer"
        this.onSearchChange(null);
      }
    }
  }

  hideItems(){
    this.hideTime = new Date();
    if (this.itemContainerClass === "showItemContainer") {
      this.itemContainerClass = "hideItemContainer"
    }
  }

  selectElement(i : {origItem,htmlValue,match}) {
    this.selectedElement = i.origItem;
    this.inputField.nativeElement.value = i.origItem[this.value];
    this.onSearchChange(null);
  }


  onSearchChange(searchValue : string){
    this.modelItems = [];
    this.origItems.forEach(item => {
      if(searchValue === null || searchValue === undefined
        || searchValue.length === 0){
        this.modelItems.push({origItem: item, htmlValue : item[this.value], match:false});
      }else {
        var pattern = '.*' + searchValue + '.*';
        if (item[this.value].match(pattern) != null) {
          var replacement = '<span style="font-weight:bold; color:forestgreen">' + searchValue + '</span>'
          var htmlValue = item[this.value].replace(new RegExp(searchValue,'g'),replacement);
          this.modelItems.push({origItem: item, htmlValue: htmlValue, match:true})
        } else {
          // skip
        }
      }
    });

  }

  inputFocusOut() {
    this.hideItems();
    this.checkInputFieldTextContent();
  }

  private checkInputFieldTextContent() {
    if(this.selectedElement === undefined ||
      (this.inputField.nativeElement.value != this.selectedElement.value)){
      this.inputField.nativeElement.value = '';
      this.selectedElement = undefined;
    }
  }
}
