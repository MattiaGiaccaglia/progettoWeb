import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrazioneComponent } from './Component/Registrazione/registrazione.component';
import { LoginComponent } from './Component/Login/login.component';

const routes: Routes = [
  { path: "Registrazione", component: RegistrazioneComponent },
  { path: "Login", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
