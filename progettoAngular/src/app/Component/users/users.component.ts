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
  public user: userList;
  displayedColumns: string[] = ['id', 'nome', 'cognome'];
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.getAllUsers();
  }

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

  public getUserById(id: number): void{
    this.userService.getUserById(id).subscribe(
      (response: userList) =>{
        this.user = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessun utente con il seguente id " + id);
      }
    );
  }
}
