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

  //Ottengo messaggi
  public getMessages(): Observable<messagesList[]>{
    return this.Http.get<messagesList[]>(`${this.constants.baseUrl}/api/messages/getMessages`)
  }

  //Ottengo messagi a partire dall'ID
  public getMessageByID(messageID: number): Observable<messagesList>{
    return this.Http.get<messagesList>(`${this.constants.baseUrl}/api/messages/getMessageByID/${messageID}`)
  }
}
