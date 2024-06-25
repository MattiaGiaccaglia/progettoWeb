import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Importa Router
import { UserService } from '../../Service/User/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})

export class UserComponent {
  user: any;
  modifica: boolean = false;
  tempNome: string = ''; // Variabile temporanea per memorizzare il nome originale
  tempCognome: string = ''; // Variabile temporanea per memorizzare il cognome originale

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = params['id'];
      this.userService.getUserById(userId).subscribe(user => {
        this.user = user;
        this.tempNome = user.nome;
        this.tempCognome = user.cognome;
      });
    });
  }

  eliminaUtente(): void {
    if (confirm('Sei sicuro di voler eliminare questo utente?')) {
      this.userService.deleteUser(this.user.id).subscribe(
        response => {
          this.showSnackbar('Utente eliminato correttamente.');
          this.reloadPage();
        },
        error =>{
          this.showSnackbar('Impossibile elimiare utente. Si è verificato un errore.');
        }
      );
    }
  }
  

  confermoModifica(): void {
    this.userService.modifyUser(this.user).subscribe(
      response => {
        this.modifica = false;
        this.reloadPage();
        this.showSnackbar('Utente aggiornato correttamente.');
      },
      error =>{
        this.reloadPage()
        this.showSnackbar('Impossibile aggiornare utente. Si è verificato un errore.');
      }
    );
  }

  inizioModifica(): void {
    this.modifica = true;
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
