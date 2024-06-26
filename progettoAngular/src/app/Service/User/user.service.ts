import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConstantsService } from '../../constants.service';
import { userList } from '../../List/userList';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getAllUsers(): Observable<userList[]>{
    return this.Http.get<userList[]>(`${this.constants.baseUrl}/api/user/getAllUsers`)
  }

  public getUserById(UserId: number): Observable<userList>{
    return this.Http.get<userList>(`${this.constants.baseUrl}/api/user/getUser/${UserId}`)
  }

  public getUserByUsername(username: string): Observable<userList>{
    return this.Http.get<userList>(`${this.constants.baseUrl}/api/user/getUserByUsername/${username}`)
  }
  
  public deleteUser(UserId: number): Observable<String>{
    return this.Http.delete<String>(`${this.constants.baseUrl}/api/user/deleteUser/${UserId}`)
  }

  public modifyUser(User: userList): Observable<String>{
    return this.Http.put<String>(`${this.constants.baseUrl}/api/user/modifyUser`, User)
  }
}
