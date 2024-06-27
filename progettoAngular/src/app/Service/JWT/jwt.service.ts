import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../../models/user.model';
import { ConstantsService } from '../../constants.service';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser: string;
  private isloggedIn = new BehaviorSubject<boolean>(false);
  private isAuthenticateSubject = new BehaviorSubject<boolean>(false);
  user: User | undefined

  constructor(private router: Router, private http:HttpClient, private constants: ConstantsService) { }

  //Creo nuovo user autenticato
  createUser(username: string, id: number, token: string, expirationDate: Date){
    this.user = new User(username, id, token, expirationDate);
    this.isloggedIn.next(true);
  }

  //Effettuo registrazione
  register(signRequest: any): Observable<any>{
    return this.http.post(`${this.constants.baseUrl}/api/user/registrazione`, signRequest)
  }

  //Effettuo login
  login(loginRequest: any): Observable<any>{
    return this.http.post(`${this.constants.baseUrl}/api/user/login`, loginRequest).pipe(
      tap((response: any) => {
        this.doLoginUser(loginRequest.username, response.token)}));
  }

  private doLoginUser(username: string, token: any){
    this.loggedUser = username;
    this.isAuthenticateSubject.next(true);
    this.storeJwtToken(token);
    this.isloggedIn.next(true);
  }

  storeJwtToken(jwt: string) {
    localStorage.setItem(this.JWT_TOKEN, jwt);
  }

  logout(){
    this.isloggedIn.next(false);
    localStorage.removeItem(this.JWT_TOKEN);
    localStorage.removeItem('user');
    this.isAuthenticateSubject.next(false);
    this.router.navigate(['/login'])
  }

  get isLoggedIn(): Observable<boolean> {
    return this.isloggedIn.asObservable();
  }

  //Ottengo utente loggato
  get getloggedUser() {
    return this.loggedUser;
  }
}
