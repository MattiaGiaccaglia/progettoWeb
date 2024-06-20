import { Role } from "../Component/User/role.enum";

export interface userList{
    id: number;
    nome: string;
    cognome: string;
    email: string;
    username: string;
    password: string;
    telefono: string;
    ruolo: Role;
}