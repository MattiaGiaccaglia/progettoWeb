import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = ["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http:HttpClient) { }

  register(signRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/registrazione', signRequest)
  }

  login(loginRequest: any): Observable<any>{
    return this.http.post(baseUrl + '/api/user/login', loginRequest)
  }
}
