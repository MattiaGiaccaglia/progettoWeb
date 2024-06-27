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

  //Ottengo tutti gli utenti registrati
  public getAllUsers(): Observable<userList[]>{
    return this.Http.get<userList[]>(`${this.constants.baseUrl}/api/user/getAllUsers`)
  }

  //Ottengo gli utenti a partire dall'ID
  public getUserById(UserId: number): Observable<userList>{
    return this.Http.get<userList>(`${this.constants.baseUrl}/api/user/getUser/${UserId}`)
  }

  //Ottengo gli utenti a partire dall'username
  public getUserByUsername(username: string): Observable<userList>{
    return this.Http.get<userList>(`${this.constants.baseUrl}/api/user/getUserByUsername/${username}`)
  }
  
  //Elimino utente
  public deleteUser(UserId: number): Observable<String>{
    return this.Http.delete<String>(`${this.constants.baseUrl}/api/user/deleteUser/${UserId}`)
  }

  //Modifico utente
  public modifyUser(User: userList): Observable<String>{
    return this.Http.put<String>(`${this.constants.baseUrl}/api/user/modifyUser`, User)
  }
}
