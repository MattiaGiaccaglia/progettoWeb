import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { Role } from '../../Component/User/role.enum';

@Injectable({
  providedIn: 'root'
})
export class AdministratorService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public modifyUserRole(userID: number, userRole: string): Observable<String> {
    return this.Http.put<String>(`${this.constants.baseUrl}/api/administrator/modifyUserRole/${userID}/${userRole}`, {});
  }
  
}
