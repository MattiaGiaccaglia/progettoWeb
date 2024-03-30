import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrazioneComponent } from './Component/Registrazione/registrazione.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Component/Login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrazioneComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
