import { Component, OnInit } from '@angular/core';
import { Consulting } from 'src/app/model/Consulting';
import { DrugReservation } from 'src/app/model/DrugReservation';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: DrugReservation[];
  reservedConsultings: Consulting[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getMyReservations().subscribe(( data: DrugReservation[]) => {
      this.reservations =data;
    })

    this.userService.getMyReservedConsultings(). subscribe((data:Consulting[]) =>{
      this.reservedConsultings = data;
    })
  }


  otkazi(reservationId) {
    console.log(reservationId);

    this.userService.cancelReservation(reservationId).subscribe(data => {
      console.log(data);
    })
  }

  cancelConsulting(consultingid) {
    console.log(consultingid);

    this.userService.cancelConsulting(consultingid).subscribe( data => {
      console.log(data);
    })
    
  }

}
