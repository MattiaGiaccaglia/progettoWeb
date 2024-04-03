import { userList } from "./userList";
import { chatList } from "./chatList"; 


export interface assistanceList{
    id: number;
    staff: userList;
    chat: chatList;
}