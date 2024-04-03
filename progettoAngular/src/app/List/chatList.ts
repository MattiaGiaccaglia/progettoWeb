import { assistanceList } from "./assistanceList";
import { messagesList } from "./messagesList";
import { userList } from "./userList";

export interface chatList{
    id: number;
    close: boolean;
    staff: userList;
    utente: userList;
    messaggi: messagesList[];
    assistance: assistanceList;
}