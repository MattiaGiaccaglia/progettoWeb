import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrazioneComponent } from './Component/Registrazione/registrazione.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Component/Login/login.component';
import { UserComponent } from './Component/User/user.component';
import { AuthInterceptorProvider } from './Interceptor/auth.interceptor';
import { UserService } from './Service/User/user.service';


@NgModule({
  declarations: [
    AppComponent,
    RegistrazioneComponent,
    LoginComponent,
    UserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers:[
    AuthInterceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
