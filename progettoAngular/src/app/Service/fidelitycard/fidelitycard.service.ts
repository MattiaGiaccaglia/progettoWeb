import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { fidelityCardList } from '../../List/fidelitycardList';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FidelitycardService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getFidelityCards(): Observable<fidelityCardList[]>{
    return this.Http.get<fidelityCardList[]>(`${this.constants.baseUrl}/api/fidelityCard/getFidelityCards`)
  }

  public getFidelityCardUser(userID: number): Observable<fidelityCardList[]>{
    return this.Http.get<fidelityCardList[]>(`${this.constants.baseUrl}/api/fidelityCard/getFidelityCardUser/${userID}`)
  }

  public addFidelityCard(fidelityCard: fidelityCardList): Observable<String>{
     return this.Http.post<String>(`${this.constants.baseUrl}/api/fidelityCard/addFidelityCard`, fidelityCard)
  }
}
