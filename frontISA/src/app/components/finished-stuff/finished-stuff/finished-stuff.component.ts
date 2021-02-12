import { DOCUMENT } from '@angular/common';
import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Consulting } from 'src/app/model/Consulting';
import { Examination } from 'src/app/model/Examination';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-finished-stuff',
  templateUrl: './finished-stuff.component.html',
  styleUrls: ['./finished-stuff.component.css']
})
export class FinishedStuffComponent implements OnInit {
 
 selectedConsulting: Consulting;

  ratingForm = this.fb.group({
    what: ['',Validators.required],
    rating: ['' ,Validators.required]
  });

  finishedConsultings: Consulting[];
  finishedExaminations: Examination[];

  ratingConsulting = false;

  drugB=false;

  constructor(private fb: FormBuilder,private userService: UserService ) { }

  ngOnInit() {

    this.userService.getFinishedConsultings().subscribe( (data: Consulting[])=>{
      this.finishedConsultings = data;
    })

    this.userService.getFinishedExaminations() .subscribe( (data: Examination[]) =>{
      this.finishedExaminations = data;

    } )
    
     
  }


  oceni(consulting: Consulting) {
   
    document.getElementById("myModal").style.display="block";
    this.selectedConsulting = consulting;
    console.log(consulting);
    
   
  }


  rate() {
    console.log(this.ratingForm.value);

    if(this.selectedConsulting !== null) {
      this.userService.rateConsulting(this.ratingForm.value , this.selectedConsulting.id).subscribe( data =>{
        console.log(data);
      })
    }
  }


  closeModal(){
    document.getElementById("myModal").style.display="none";

    this.selectedConsulting = null;
  }
  

 

}
