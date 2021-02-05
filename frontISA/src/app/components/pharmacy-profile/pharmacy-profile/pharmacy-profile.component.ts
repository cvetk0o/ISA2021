import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { Error } from 'src/app/model/Error';
import { Examination } from 'src/app/model/Examination';
import { PharmacyService } from 'src/app/services/pharmacy-service/pharmacy.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {
  pharmacyId;
  examinations: Examination[];
  constructor(private route: ActivatedRoute ,private pharmacyService : PharmacyService , private userService: UserService) { }

  ngOnInit() {
    this.pharmacyId = this.route.snapshot.params.id;
    
    this.pharmacyService.getAvailableExaminations(this.pharmacyId).subscribe ((data:Examination[])=> {
     
      this.examinations = data;
      console.log(data)
    
    }, (error:Error) => {
      alert(error.errors)
    })
  }


  makeReservation(examinationId) {
    
    this.userService.makeReservation(examinationId).subscribe( (data:ApiResponse) => {
      alert(data.message);
      
    window.location.reload();
    }, (error:Error) => {
      alert(error.errors);
    })
    
  
  }

}
