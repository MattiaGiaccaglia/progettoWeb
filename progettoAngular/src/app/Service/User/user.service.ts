import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserComponent } from '../../Component/User/user.component';
import { ConstantsService } from '../../constants.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getAllUsers(): Observable<UserComponent[]>{
    return this.Http.get<UserComponent[]>(`${this.constants.baseUrl}/api/user/getAllUsers`)
  }

  public getUserById(UserId: number): Observable<UserComponent>{
    return this.Http.get<UserComponent>(`${this.constants.baseUrl}/api/user/getUser/${UserId}`)
  }
  
  public deleteUser(UserId: number): Observable<String>{
    return this.Http.delete<String>(`${this.constants.baseUrl}/api/user/deleteUser/${UserId}`)
  }

  public modifyUser(): Observable<String>{
    return this.Http.put<String>(`${this.constants.baseUrl}/api/user/modifyUser`, UserComponent)
  }
}
