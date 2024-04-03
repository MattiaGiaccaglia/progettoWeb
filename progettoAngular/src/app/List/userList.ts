import { Role } from "../Component/user/role.enum";

export interface userList{
    id: number;
    nome: string;
    cognome: string;
    email: string;
    password: string;
    telefono: string;
    ruolo: Role;
}