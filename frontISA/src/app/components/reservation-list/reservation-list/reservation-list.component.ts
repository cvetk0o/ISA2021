import { Component, OnInit } from '@angular/core';
import { DrugReservation } from 'src/app/model/DrugReservation';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: DrugReservation[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getMyReservations().subscribe(( data: DrugReservation[]) => {
      this.reservations =data;
    })
  }


  otkazi(reservationId) {
    console.log(reservationId);

    this.userService.cancelReservation(reservationId).subscribe(data => {
      console.log(data);
    })
  }

}
