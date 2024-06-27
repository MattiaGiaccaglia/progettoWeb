import { Component } from '@angular/core';
import { JwtService } from './Service/JWT/jwt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'loyalityPlatform';

  constructor(public jwtService: JwtService){}

  onLogout(){
    this.jwtService.logout();
  }

  ngOnInit(): void{
    //Se esiste già uno user nel LocalStorage e l'expiration date non è scaduta, allora rimango loggato
    if(localStorage.getItem('user')){ 
      const user = JSON.parse(localStorage.getItem('user')!)
      const dateOne = new Date()
      //Controllo expiration date
      if(Date.parse(user._expirationDate) < dateOne.getTime()) 
        this.onLogout(); 
      else 
        this.jwtService.createUser(user.username, user.id, user._token, user._expirationDate) 
    }
  }
}
