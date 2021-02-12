import { Component, OnInit } from '@angular/core';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Consulting } from 'src/app/model/Consulting';
import { DrugReservation } from 'src/app/model/DrugReservation';
import { Error } from 'src/app/model/Error';
import { Examination } from 'src/app/model/Examination';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: DrugReservation[];
  reservedConsultings: Consulting[];
  reservedExaminations: Examination[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getMyReservations().subscribe(( data: DrugReservation[]) => {
      this.reservations =data;
    })

    this.userService.getMyReservedConsultings(). subscribe((data:Consulting[]) =>{
      this.reservedConsultings = data;
    })

    this.userService.getMyReservedExaminations().subscribe((data:Examination[]) => {
      this.reservedExaminations=data;
    } ,(error: Error) => {
      alert(error.errors);
    })
  }


  otkazi(reservationId) {
    console.log(reservationId);

    this.userService.cancelReservation(reservationId).subscribe((data:ApiResponse) => {
      alert(data.message);
      window.location.reload();
    }, (error:Error) => {
      alert(error.errors)
    })
  }

  cancelConsulting(consultingid) {
    console.log(consultingid);

    this.userService.cancelConsulting(consultingid).subscribe( (data:ApiResponse) => {
      alert(data.message);
      window.location.reload();

    }, (error:Error) =>{
      alert(error.errors);
    })
    
  }

  cancelExamination(examinationId) {
    this.userService.cancelExamination(examinationId) . subscribe((data:ApiResponse) => {
      alert(data.message);
      window.location.reload();
    },(error:Error) =>{
      alert(error.errors)
    })
  }

}
