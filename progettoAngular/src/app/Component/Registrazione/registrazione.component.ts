import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrl: './registrazione.component.css'
})
export class RegistrazioneComponent {

  registerForm: FormGroup | undefined;

  constructor(
    private service: JwtService,
    private fb:FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar
  ){}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nome: ['', Validators.required],
      cognome: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]], 
      email: ['', [Validators.required, Validators.email]], 
      telefono: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]]
    });
}

submitForm() {
  this.service.register(this.registerForm.value).subscribe(
    (data: any) =>{
      this.showSnackbar('Registrazione effettuata correttamente.');
      this.router.navigate(['/login'])
    },
    error => {
      this.showSnackbar('Impossibile effettuare registrazione, dati errati.');
    }
  );
}

showSnackbar(message: string): void {
  this.snackBar.open(message, 'Chiudi', {
    duration: 5000,
    verticalPosition: 'top'
  });
}
}
