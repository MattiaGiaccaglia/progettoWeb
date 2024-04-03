import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../Service/JWT/jwt.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConstantsService } from '../../constants.service';


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
    private constants: ConstantsService
  ){}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nome : ['', Validators.required],
      cognome : ['', Validators.required],
      username : ['', Validators.required],
      password : ['', Validators.required],
      email : ['', Validators.required],
      telefono : ['', Validators.required]
    })
    }

    submitForm() {
      console.log(this.registerForm.value);
      this.service.register(this.registerForm.value).subscribe(
      );
      this.router.navigate(['/Login'])
    }       
}
