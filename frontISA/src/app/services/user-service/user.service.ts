import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  restUrl = 'http://localhost:8080/api/userController/';

  constructor( private http: HttpClient) { }

  getMyInfo() {
    return this.http.get( `${this.restUrl}getMyInfo`)
  }

  updateMyInfo(info) {
    return this.http.post( `${this.restUrl}user` , info );
  }

  getMyReservations() {
    return this.http.get(`${this.restUrl}getMyReservations`);
  }

  cancelReservation(reservationId) {
    return this.http.get(`${this.restUrl}cancelReservation/${reservationId}`);
  }

  getMyReservedConsultings() {
    return this.http.get(`${this.restUrl}reservedConsultings`);
  }

  cancelConsulting(consultingid) {
    return this.http.get(`${this.restUrl}cancelConsulting/${consultingid}`);
  }

  updatePassword(info) {
    return this.http.post( `${this.restUrl}updatePassword` , info );
  }


  makeReservation(examinationId) {
    return this.http.get(`${this.restUrl}examinations/reservation/${examinationId}`); 
  }

  getMyReservedExaminations(){
    return this.http.get(`${this.restUrl}reservedExaminations`);
  }

  cancelExamination(examinationId){

    return this.http.get(`${this.restUrl}cancelExamination/${examinationId}`);
  }

}


