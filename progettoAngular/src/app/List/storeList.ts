import { userList } from "./userList";

export interface storeList {
    id: number;
    nome: string;
    luogo: string;
    proprietario: userList;
    dipendenti: userList[];
    programma: number;
}
