import { Component } from '@angular/core';
import { JwtService } from './Service/JWT/jwt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'progetto';

  constructor(public jwtService: JwtService){}

  onLogout(){
    this.jwtService.logout();
  }

  ngOnInit(): void{
    if(localStorage.getItem('user')){ //Se esiste già uno user nel LocalStorage e l'expiration date non è passata, allora rimango loggato
      const user = JSON.parse(localStorage.getItem('user')!)
      const dateOne = new Date()
      if(Date.parse(user._expirationDate) < dateOne.getTime()) 
        this.onLogout(); 
      else 
        this.jwtService.createUser(user.username, user.id, user._token, user._expirationDate) 
    }
  }
}
