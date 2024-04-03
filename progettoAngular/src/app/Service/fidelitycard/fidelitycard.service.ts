import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { fidelityCardList } from '../../List/fidelity-cardList';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FidelitycardService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getFidelityCards(): Observable<fidelityCardList[]>{
    return this.Http.get<fidelityCardList[]>(`${this.constants.baseUrl}/api/fidelityCard/getFidelityCards`)
  }
}
