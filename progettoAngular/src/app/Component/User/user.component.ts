import { Component, OnInit } from '@angular/core';
import { UserService } from '../../Service/User/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { userList } from '../../List/userList';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {
  public users: userList[];
  constructor(private userService: UserService){}

  ngOnInit() {
    this.getAllUsers();
  }

  public getAllUsers(): void{
    this.userService.getAllUsers().subscribe(
      (response: userList[]) =>{
        this.users = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }
}