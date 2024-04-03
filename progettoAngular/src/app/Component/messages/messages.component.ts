import { Component } from '@angular/core';
import { messagesList } from '../../List/messagesList';
import { MessagesService } from '../../Service/messages/messages.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css'
})
export class MessagesComponent {
  public messages: messagesList[];
  constructor(private messagesService: MessagesService){}

  ngOnInit() {
    this.getMessages();
  }

  public getMessages(): void{
    this.messagesService.getMessages().subscribe(
      (response: messagesList[]) =>{
        this.messages = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }
}
