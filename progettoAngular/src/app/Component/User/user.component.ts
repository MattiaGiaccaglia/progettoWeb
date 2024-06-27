import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Importa Router
import { UserService } from '../../Service/User/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { JwtService } from '../../Service/JWT/jwt.service';
import { userList } from '../../List/userList';
import { Role } from './role.enum';
import { AdministratorService } from '../../Service/administrator/administrator.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})

export class UserComponent {
  user: userList;
  modifica: boolean = false;

  // Variabili temporanee per memorizzare il nome e cognome originale
  tempNome: string = ''; 
  tempCognome: string = '';

  admin: userList;
  isAdmin: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private snackBar: MatSnackBar,
    private router: Router,
    private jwtService: JwtService,
    private administratorService: AdministratorService
  ) { }

  //Ottengo singolo utente a partire dal suo ID
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = params['id'];
      this.userService.getUserById(userId).subscribe(user => {
        this.user = user;
        this.tempNome = user.nome;
        this.tempCognome = user.cognome;
      });
    });
    this.checkRole();
  }

  //Elimino utente
  deleteUser(): void {
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

  //Modifico utente
  confirmModify(): void {
    //Se utente loggato è amministratore, allora cambio anche il ruolo
    if(this.isAdmin){
      this.administratorService.modifyUserRole(this.user.id, this.user.ruolo.toString()).subscribe(
        roleResponse => {
          this.modifica = true;
        })
    }
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

  modifyUser(): void {
    this.modifica = true;
  }
ù
  //Verifico se utente loggato è amministratore
  checkRole(): void {
    this.userService.getUserByUsername(this.jwtService.user.username).subscribe((res: userList) => {
      this.admin = res;
      this.isAdmin = this.admin.ruolo === Role.Amministratore;
    });
  }

  //Ricarico pagina
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
