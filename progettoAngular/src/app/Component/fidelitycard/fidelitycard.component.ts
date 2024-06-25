import { Component } from '@angular/core';
import { fidelityCardList } from '../../List/fidelitycardList';
import { FidelitycardService } from '../../Service/fidelitycard/fidelitycard.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { userList } from '../../List/userList';
import { UserService } from '../../Service/User/user.service';
import { forkJoin } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fidelitycard',
  templateUrl: './fidelitycard.component.html',
  styleUrl: './fidelitycard.component.css'
})
export class FidelitycardComponent {
  public fidelitycards: fidelityCardList[];
  displayedColumns: string[] = ['id', 'utente', 'venditore'];
  aggiungi: boolean = false;

  //Ricavo i due username dal form che compila l'utente
  userUsername: string;
  vendorUsername: string;

  //Recupero gli username e li salvo in questi user
  userUser: userList;
  userVendor: userList;

  fidelitycard: fidelityCardList;

  constructor(private fidelitycardService: FidelitycardService, 
    private userService: UserService, 
    private snackBar: MatSnackBar,
    private router: Router
  ){}

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

  onSubmit(): void {
    this.aggiungi = true;
  }

  addFidelityCard(): void {
    forkJoin({
      user: this.userService.getUserByUsername(this.userUsername),
      vendor: this.userService.getUserByUsername(this.vendorUsername)
    }).subscribe({
      next: ({ user, vendor }) => {
        const newFidelityCard: fidelityCardList = {
          user: user,
          vendorFidelity: vendor,
          length: null,
          id: null
        };
        this.fidelitycardService.addFidelityCard(newFidelityCard).subscribe({
          next: response => {
            this.aggiungi = false; // Chiudi il form di aggiunta
            this.reloadPage();
            this.showSnackbar("Fidelity card aggiunta correttamente");
          },
          error: error => {
            this.showSnackbar('Impossibile aggiungere FidelityCard. Si è verificato un errore.');
          }
        });
      },
      error: (error: HttpErrorResponse) => {
        // Gestisco errori di recupero utente o venditore
        this.showSnackbar('Errore nel recupero delle informazioni utente o venditore.');
      }
    });
  }
  

  reloadPage(): void {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  showSnackbar(message: string): void {
    this.snackBar.open(message, 'Chiudi', {
      duration: 5000,
      verticalPosition: 'top'
    });
  }
}
