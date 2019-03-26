import {Component, ElementRef, Input, OnInit, Output, ViewChild, EventEmitter, forwardRef} from '@angular/core';
import {
  AbstractControl,
  ControlValueAccessor,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR,
  ValidationErrors,
  Validator
} from "@angular/forms";

@Component({
  selector: 'fuz-number',
  templateUrl: './fuz-number.component.html',
  styleUrls: ['./fuz-number.component.css'],
  providers : [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FuzNumberComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => FuzNumberComponent),
      multi: true
    }
  ]
})
export class FuzNumberComponent implements ControlValueAccessor, Validator {

  constructor() { }

  @Input() label: string;
  @Input() unit: string;
  @Input() required : boolean = false;

  @ViewChild("numberInput") inputField: ElementRef;

  private numberValue : number;
  isDisabled :boolean = false;

  set value(value: number){
    this.numberValue = value;
    if(value !== undefined) {
      this.inputField.nativeElement.value = value;
    }
    this.setCssClass(value);
    this.propagateChange(this.numberValue);
  }

  get value() : number{
    return this.numberValue;
  }

  private propagateChange = (_: any) => { };
  // a from itt értesül arról, hogy változott az input értéke
  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }

  // most nem használjuk
  registerOnTouched(fn: any): void {
  }

  setDisabledState(isDisabled: boolean): void {
    this.isDisabled = isDisabled;
  }

  writeValue(obj: any): void {
    if(obj){
      this.value = obj;
    }else{
      this.value = null;
    }
  }

  registerOnValidatorChange(fn: () => void): void {
  }

  public errors : {code:string, message:string} [] = [];
  // returns null when valid else the validation object
  // in this case we're checking if the json parsing has
  // passed or failed from the onChange method
  validate(control: AbstractControl): ValidationErrors | null {
    this.errors = [];
    if(this.value === undefined || this.value === null){
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

  inputClass : string = "";
  onInputChange(event: any) {
    var isValid : boolean = Number.isInteger(Number(event.target.value));
    if(isValid){
      if(event.target.value == ""){
        this.value = null;
      }else {
        this.value = Number(event.target.value);
      }
    }else{
      this.value = event.target.value;
    }
    this.setCssClass(this.value);
  }

  onKeyDown(event: any) : boolean {
    console.log('onKeyDown: ' + event.key);
    if(Number.isInteger(Number(event.key))){
      return true;
    }else{
      return false;
    }
  }

  private setCssClass(value: number | string) {
    if(value === null || value === undefined) {
      if(this.required){
        this.inputClass = "input-error"
      }else {
        this.inputClass = "";
      }
    }else if(Number.isInteger(Number(value))){
      this.inputClass = "input-valid"
    }else{
      this.inputClass = "input-error"
    }
  }




}
