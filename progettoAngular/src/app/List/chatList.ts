import { assistanceList } from "./assistanceList";
import { messaggiList } from "./messagesList";
import { userList } from "./userList";

export interface chatList{
    id: number;
    close: boolean;
    staff: userList;
    utente: userList;
    messaggi: messaggiList[];
    assistance: assistanceList;
}