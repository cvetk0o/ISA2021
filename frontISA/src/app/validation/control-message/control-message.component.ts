import { Component, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ValidationServiceService } from '../validation-service.service';

@Component({
  selector: 'app-control-message',
   template: `<div *ngIf="errorMessage !== null">{{errorMessage}}</div>`,
  styleUrls: ['./control-message.component.css']
})
export class ControlMessageComponent {
  @Input() control: FormControl;
  @Input() group: FormGroup;

  constructor() {}

  get errorMessage() {
    for (let propertyName in this.control.errors) {
      if (this.control.errors.hasOwnProperty(propertyName) && this.control.touched) {
        return ValidationServiceService.getValidatorErrorMessage(propertyName, this.control.errors[propertyName]);
      }
    }

   

    return null;
  }
}