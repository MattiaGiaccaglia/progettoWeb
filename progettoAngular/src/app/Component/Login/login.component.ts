import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm : FormGroup;

  constructor(
    private jwtService: JwtService,
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar
  ){}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]]
    })
  }

  submitForm(){
    this.jwtService.login(this.loginForm.value).subscribe((data: any) =>{
    //Decodifico il token
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(data.token);
    const expirationDate = helper.getTokenExpirationDate(data.token);

    //Creo nuovo user autenticato
    this.jwtService.createUser(decodedToken.sub, data.localID, data.token, expirationDate!)
    localStorage.setItem('user', JSON.stringify(this.jwtService.user))
    this.router.navigate(['/profile']);
    }, 
    error => {
      this.showSnackbar('Impossibile effettuare login, dati errati.');
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
