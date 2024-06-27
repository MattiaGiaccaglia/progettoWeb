import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessagesService } from '../../Service/messages/messages.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css'
})
export class MessagesComponent {
message: any;

constructor(
  private route: ActivatedRoute,
  private messagesService: MessagesService,
) { }

//Ottengo messaggio a partire dal suo ID
ngOnInit(): void {
  this.route.params.subscribe(params => {
    const messageID = params['id'];
    this.messagesService.getMessageByID(messageID).subscribe(message => {
      this.message = message;
    });
  });
}
}
