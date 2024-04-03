import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../../models/user.model';

const baseUrl = ["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser: string;
  private isloggedIn = new BehaviorSubject<boolean>(false);
  private isAuthenticateSubject = new BehaviorSubject<boolean>(false);
  user: User | undefined

  constructor(private router: Router, private http:HttpClient) { }

  createUser(username: string, id: string, token: string, expirationDate: Date){
    this.user = new User(username, id, token, expirationDate);
    this.isloggedIn.next(true);
  }

  register(signRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/registrazione', signRequest)
  }

  login(loginRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/login', loginRequest).pipe(
      tap((response: any) => this.doLoginUser(loginRequest.username, response.token)));
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
    this.router.navigate(['/Login'])
  }

  get isLoggedIn(): Observable<boolean> {
    return this.isloggedIn.asObservable();
  }

  get getloggedUser() {
    return this.loggedUser;
  }
}
