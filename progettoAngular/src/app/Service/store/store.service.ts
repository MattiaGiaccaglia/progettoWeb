import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { storeList } from '../../List/storeList';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  //Ottengo tutti gli stores
  public getAllStores(): Observable<storeList[]>{
    return this.Http.get<storeList[]>(`${this.constants.baseUrl}/api/store/getAllStores`)
  }

  //Ottengo gli store a partire dall'ID
  public getStore(storeID: number): Observable<storeList>{
    return this.Http.get<storeList>(`${this.constants.baseUrl}/api/store/getStore/${storeID}`)
  }
}
