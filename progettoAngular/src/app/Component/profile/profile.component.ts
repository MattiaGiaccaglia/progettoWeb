import { Component } from '@angular/core';
import { UserService } from '../../Service/User/user.service';
import { JwtService } from '../../Service/JWT/jwt.service';
import { userList } from '../../List/userList';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: any;

  constructor(private userService: UserService, private jwtService: JwtService) {}

  ngOnInit(): void {
    //Prendo i dati dell'utente loggato
    this.userService.getUserByUsername(this.jwtService.user.username).subscribe((res: userList) => {
      console.log(res);
      this.user = res;
    });
  }
  
  isObject(value: any): boolean { return typeof value === 'object'; } //Lo uso per non farmi dare errori in console, dato che il component.html renderizza più veloce della subscribe nel component.ts
}

