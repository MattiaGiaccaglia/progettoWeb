import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrl: './registrazione.component.css'
})
export class RegistrazioneComponent {

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
      username : ['', Validators.required],
      password : ['', Validators.required, Validators.minLength(8)],
      email : ['', Validators.required, Validators.email],
      telefono : ['', Validators.required, Validators.minLength(10), Validators.maxLength(10)]
    })
    }

    submitForm(){
      console.log(this.registerForm.value);
      this.service.register(this.registerForm.value).subscribe(
        (response) => {
          if(response.id != null)
          alert("Benvenuto " + response.nome)
        }
      )
    }
}
