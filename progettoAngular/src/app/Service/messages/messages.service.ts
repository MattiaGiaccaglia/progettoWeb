import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { messagesList } from '../../List/messagesList';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getMessages(): Observable<messagesList[]>{
    return this.Http.get<messagesList[]>(`${this.constants.baseUrl}/api/messages/getMessages`)
  }
}
