import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatService } from '../../Service/chat/chat.service';
import { chatList } from '../../List/chatList';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrl: './chats.component.css'
})
export class ChatsComponent {
chat: chatList;

constructor(
  private route: ActivatedRoute,
  private chatService: ChatService,
) { }


//Ricavo singole chat a partire da ID
ngOnInit(): void {
  this.route.params.subscribe(params => {
    const chatID = params['id'];
    this.chatService.getChat(chatID).subscribe(chat => {
      this.chat = chat;
    });
  });
}
}
