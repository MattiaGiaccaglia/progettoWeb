import { Component } from '@angular/core';
import { messagesList } from '../../List/messagesList';
import { MessagesService } from '../../Service/messages/messages.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-messages',
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent {
  public messages: messagesList[];
  displayedColumns: string[] = ['id', 'mittente', 'destinatario'];

  constructor(private messagesService: MessagesService){}

  ngOnInit() {
    this.getMessages();
  }

  //Ricavo tutti i messaggi
  public getMessages(): void{
    this.messagesService.getMessages().subscribe(
      (response: messagesList[]) =>{
        this.messages = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessun messaggio presente");
      }
    );
  }
}
