import { userList } from "./userList";

export interface fidelityCardList{
    length: number;
    id: number,
    user: userList,
    vendorFidelity: userList,
}