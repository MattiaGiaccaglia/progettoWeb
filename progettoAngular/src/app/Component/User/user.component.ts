import { Component } from '@angular/core';
import { Role } from './role.enum';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  id: number;
  nome: string;
  cognome: string;
  email: string;
  password: string;
  telefono: string;
  ruolo: Role;
}
