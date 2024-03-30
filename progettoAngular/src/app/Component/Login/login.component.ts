import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm : FormGroup;

  constructor(
    private service: JwtService,
    private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }
    
  submitForm(){
    this.service.login(this.loginForm.value).subscribe(
      (response) => {
        console.log(response);
        if(response.jwt != null){
          alert("Accesso effettuato: " + response.jwt)
        }
      }
    )
  }



}
