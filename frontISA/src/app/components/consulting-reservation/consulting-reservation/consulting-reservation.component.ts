import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-consulting-reservation',
  templateUrl: './consulting-reservation.component.html',
  styleUrls: ['./consulting-reservation.component.css']
})
export class ConsultingReservationComponent implements OnInit {

  dateControl = new FormControl(new Date);
  timeControl = new FormControl(new Date);


  constructor() { }

  ngOnInit() {
  }

  valuechangeDate( newValue) {
    console.log(this.dateControl.value);
  }

  valuechange( newValue) {
    console.log(this.timeControl.value);
  }

}
