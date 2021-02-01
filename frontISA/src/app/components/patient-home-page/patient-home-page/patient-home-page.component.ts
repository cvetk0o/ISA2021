import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-patient-home-page',
  templateUrl: './patient-home-page.component.html',
  styleUrls: ['./patient-home-page.component.css']
})
export class PatientHomePageComponent implements OnInit {
   myId;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getMyInfo().subscribe((data: User) => {
      console.log(data);
      
    })
  }

}
