import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { HttpClient } from '@angular/common/http';
import { chatList } from '../../List/chatList';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }
  
  //Ottengo chats
  public getChats(): Observable<chatList[]>{
    return this.Http.get<chatList[]>(`${this.constants.baseUrl}/api/chat/getChats`)
  }

  //Ottengo chat a partire dall'ID
  public getChat(chatID: number): Observable<chatList>{
    return this.Http.get<chatList>(`${this.constants.baseUrl}/api/chat/getChat/${chatID}`)
  }
}
