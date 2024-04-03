import { userList } from "./userList";

export interface reviewList{
    id: number,
    testo: String,
    valutazione: number,
    vendor: userList,
    user: userList
}
