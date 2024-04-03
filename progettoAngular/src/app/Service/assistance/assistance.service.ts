import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { assistanceList } from '../../List/assistanceList';

@Injectable({
  providedIn: 'root'
})
export class AssistanceService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getAssistance(): Observable<assistanceList[]>{
    return this.Http.get<assistanceList[]>(`${this.constants.baseUrl}/api/assistance/getAssistance`)
  }
}
