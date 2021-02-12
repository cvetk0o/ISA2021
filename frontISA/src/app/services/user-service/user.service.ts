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

  addToAlergieList(drugId) {
    return this.http.post(`${this.restUrl}drugs/alergie`,drugId )
  }


  getFinishedConsultings() {
    return this.http.get(`${this.restUrl}consultings/finished`);
  }

  getFinishedExaminations() {
    return this.http.get(`${this.restUrl}examinations/finished`);
  }



    rateConsulting(data,consultingId) {
      return this.http.post(`${this.restUrl}consultings/rate/${consultingId}` , data);
    }


    getMyPharmacists() {
      return this.http.get(`${this.restUrl}getMyPharmacists`);
    }

    ratePharmacist(data) {
      return this.http.post(`${this.restUrl}ratePharmacist` , data);
    }

    overrideRatePharmacist(data) {
      return this.http.post(`${this.restUrl}overrideRatePharmacist` , data);
    }


    getMyDermatologists() {
      return this.http.get(`${this.restUrl}getMyDermatologists`);
    }

    rateDermatologist(data) {
      return this.http.post(`${this.restUrl}rateDermatologist` , data);
    }

    overrideRateDermatologist(data) {
      return this.http.post(`${this.restUrl}overrideRateDermatologist` , data);
    }


}




