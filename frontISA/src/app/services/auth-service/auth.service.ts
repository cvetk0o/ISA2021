import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable  } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { JWToken } from 'src/app/model/JWToken';
import jwtDecode from 'jwt-decode';



@Injectable({
  providedIn: 'root'
})
export class AuthService {
  restUrl = 'http://localhost:8080/api/auth/';
  
  private currentUserSubject: BehaviorSubject<JWToken>;
  public currentUser: Observable<JWToken>;


  constructor(private http: HttpClient ) { 
    
    this.currentUserSubject = new BehaviorSubject<JWToken>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): JWToken {
    return this.currentUserSubject.value;
}

  signUp(userDetails){
    return this.http.post(`${this.restUrl}signUp`, userDetails);
  }

  signIn(email: string, password: string) {
   
    return this.http.post<any>('http://localhost:8080/api/auth/login', { email, password } )
    .pipe(map(user => {
     
      
        if (user && user.accessToken) { 
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            let token: JWToken  = jwtDecode(user.accessToken)
            token.token= user.accessToken;
            localStorage.setItem('currentUser', JSON.stringify(token));
 
            this.currentUserSubject.next(token);
        }
        let token: JWToken  = jwtDecode(user.accessToken)
        return token.role;
    }));
  }

  
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
}

getUloga(){
  
}

activateUser(id) {
 return this.http.post(`${this.restUrl}activate/${id}`, id);
}

}
