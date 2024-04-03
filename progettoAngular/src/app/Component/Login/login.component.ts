import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

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
    private router: Router
  ){}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }
    
  submitForm(){
    console.log(this.loginForm.value);
    this.jwtService.login(this.loginForm.value).subscribe((data: any) =>{

    //decodifico il token
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(data.token);
    const expirationDate = helper.getTokenExpirationDate(data.token);

    //creo User
    this.jwtService.createUser(decodedToken.sub, data.localID, data.token, expirationDate!)
    localStorage.setItem('user', JSON.stringify(this.jwtService.user))
    this.router.navigate(['/']);
    });
  }
}
