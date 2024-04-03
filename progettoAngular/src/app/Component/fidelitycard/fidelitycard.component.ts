import { Component } from '@angular/core';
import { fidelityCardList } from '../../List/fidelity-cardList';
import { FidelitycardService } from '../../Service/fidelitycard/fidelitycard.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-fidelitycard',
  templateUrl: './fidelitycard.component.html',
  styleUrl: './fidelitycard.component.css'
})
export class FidelitycardComponent {
  public fidelitycards: fidelityCardList[];
  constructor(private fidelitycardService: FidelitycardService){}

  ngOnInit() {
    this.getFidelityCards();
  }

  public getFidelityCards(): void{
    this.fidelitycardService.getFidelityCards().subscribe(
      (response: fidelityCardList[]) =>{
        this.fidelitycards = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessuna FidelityCard presente");
      }
    );
  }
}
