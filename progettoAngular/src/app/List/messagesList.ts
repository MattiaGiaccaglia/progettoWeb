import { chatList } from "./chatList";
import { userList } from "./userList";

export interface messagesList{
    id: number,
    testoMessaggio: String,
    data: Date,
    mittente: userList,
    destinatario: userList,
    chatId: chatList
}
