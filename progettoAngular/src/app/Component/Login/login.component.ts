import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConstantsService } from '../../constants.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm : FormGroup;

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private constants: ConstantsService,
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
    this.service.login(this.loginForm.value).subscribe(
    )
  }
}
