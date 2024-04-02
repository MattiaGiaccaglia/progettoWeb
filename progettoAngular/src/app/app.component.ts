import { Component, OnInit } from '@angular/core';
import { UserComponent } from './Component/User/user.component';
import { UserService } from './Service/User/user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'progetto';
  public user: UserComponent[];
  constructor(private userService: UserService){}

  ngOnInit() {
    this.getAllUsers();
  }

  public getAllUsers(): void{
    this.userService.getAllUsers().subscribe(
      (response: UserComponent[]) =>{
        this.user = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }
}
