import { Component, OnInit } from '@angular/core';
import { UserService } from '../../Service/User/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { userList } from '../../List/userList';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit {
  public users: userList[];
  displayedColumns: string[] = ['id', 'nome', 'cognome'];
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.getAllUsers();
  }

  //Ottengo tutti gli utenti registrati
  public getAllUsers(): void{
    this.userService.getAllUsers().subscribe(
      (response: userList[]) =>{
        this.users = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessun utente registrato");
      }
    );
  }
}
