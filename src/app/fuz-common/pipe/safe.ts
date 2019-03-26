import {Pipe, PipeTransform} from "@angular/core";
import {DomSanitizer} from "@angular/platform-browser";

@Pipe({name: 'safeHtml'})
export class Safe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) {
  }
  public transform(value: string) {
    return this.sanitized.bypassSecurityTrustHtml(value);
  }
}
