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
    private router: Router,
    private snackBar: MatSnackBar
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

  confermoModifica(): void {
    console.log('Confermo modifica'); //log per debug
    this.userService.modifyUser(this.user).subscribe(
      response => {
        this.modifica = false;
        console.log('Utente aggiornato correttamente');
        this.showSnackbar('Utente aggiornato correttamente.');
        this.reloadPage();
        
      },
      error => {
        console.error('Impossibile aggiornare utente');
        this.showSnackbar('Impossibile aggiornare utente. Si è verificato un errore.');
        this.reloadPage()
      }
    );
  }

  inizioModifica(): void {
    this.modifica = true;
  }

  reloadPage(): void {
    console.log('Ricaricamento pagina'); //log per debug
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
