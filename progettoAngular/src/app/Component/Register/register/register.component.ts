import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit{

  registerForm: FormGroup | undefined;


  constructor(
    private service: JwtService,
    private fb:FormBuilder
  ){

  }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nome : ['', Validators.required],
      cognome : ['', Validators.required],
      email : ['', Validators.required],
      username : ['', Validators.required],
      password : ['', Validators.required],
      telefono : ['', Validators.required]
    })
    }

    submitForm(){
      console.log(this.registerForm.value);
    }
}
