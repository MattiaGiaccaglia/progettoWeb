import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

const baseUrl = ["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser: string;
  private isAuthenticateSubject = new BehaviorSubject<boolean>(false);

  constructor(private http:HttpClient) { }

  register(signRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/registrazione', signRequest)
  }

  login(loginRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/login', loginRequest).pipe(
      tap((response: any) => this.doLoginUser(loginRequest.username, response.token))
    );
  }

  private doLoginUser(username: string, token: any){
    this.loggedUser = username;
    this.isAuthenticateSubject.next(true);
    this.storeJwtToken(token)
  }

  storeJwtToken(jwt: string) {
    localStorage.setItem(this.JWT_TOKEN, jwt);
  }

  logout(){
    localStorage.removeItem(this.JWT_TOKEN);
    this.isAuthenticateSubject.next(false);
  }
}
