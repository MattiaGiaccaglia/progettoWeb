import { Component } from '@angular/core';
import { UserService } from '../../Service/User/user.service';
import { JwtService } from '../../Service/JWT/jwt.service';
import { userList } from '../../List/userList';
import { FidelitycardService } from '../../Service/fidelitycard/fidelitycard.service';
import { fidelityCardList } from '../../List/fidelitycardList';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: userList;
  fidelityCards: fidelityCardList[] = [];
  hasFidelityCards: boolean = false; 

  constructor(private userService: UserService, private jwtService: JwtService, private fidelitycardservice: FidelitycardService) {}

  ngOnInit(): void {
    this.userService.getUserByUsername(this.jwtService.user.username).subscribe((res: userList) => {
      if (res) {
        this.user = res;
        this.fidelitycardservice.getFidelityCardUser(this.user.id).subscribe((cards: fidelityCardList[]) => {
          if (cards && cards.length > 0) {
            this.fidelityCards = cards;
            this.hasFidelityCards = true;
          }else
            this.hasFidelityCards = false;
        });
      }
    });
  }
}

