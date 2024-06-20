import { Component, OnInit } from '@angular/core';
import { chatList } from '../../List/chatList';
import { ChatService } from '../../Service/chat/chat.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{
  public chats: chatList[];

  constructor(private chatService: ChatService){}

  public getAllChats(): void{
    this.chatService.getChats().subscribe(
      (response: chatList[]) =>{
        this.chats = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  ngOnInit() {
    this.getAllChats();
  }

}
