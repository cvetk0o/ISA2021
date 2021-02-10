import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {
  restUrl = 'http://localhost:8080/api/pharmacyController/';

  constructor( private http: HttpClient) { }


  getAll(){
    return this.http.get( `${this.restUrl}pharmacies` );
  }

  searchPharm(params) {
    
    return this.http.get(`${this.restUrl}searchPharm/?${params}` );

  }


  getAvailablePharmacies( data) {

    return this.http.post( `${this.restUrl}availablePharmacies` , data);
  }

  showPharmacists( data) {

    return this.http.post( `${this.restUrl}showPharmacists`,data);
  }


  makeReservation( data) {
    return this.http.post( `${this.restUrl}makeReservation`,data);
  }

  sortPharmacies( propertie , order ) {

    return this.http.get(`${this.restUrl}sort/${propertie}/${order}`)
  }

  getAvailableExaminations(pharmacyId){

    return this.http.get(`${this.restUrl}examinations/${pharmacyId}`);
  }


  sortAvailablePharmacies(propertie , order , reservation) {

    return this.http.post(`${this.restUrl}availablePharmacies/sort/${propertie}/${order}` , reservation)

  }

 getMyPharmacies() {
   return this.http.get(`${this.restUrl}getMyPharmacies`);
 }

}
