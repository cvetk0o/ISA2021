import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-activate-user',
  templateUrl: './activate-user.component.html',
  styleUrls: ['./activate-user.component.css']
})
export class ActivateUserComponent implements OnInit {
  id: number;
  private sub: any;

  constructor(private route: ActivatedRoute , private authService: AuthService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number

      // In a real app: dispatch action to load the details here.
   });

    console.log(' id korisnika je : ' + this.id);
 
  }

  activate() {

    this.authService.activateUser(this.id).subscribe( (data) => { 
      alert('USPESNO')
    });

  }

  cancel() {

  }

}
