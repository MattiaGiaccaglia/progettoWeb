import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrazioneComponent } from './Component/registrazione/registrazione.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Component/login/login.component';
import { UserComponent } from './Component/User/user.component';
import { AuthInterceptorProvider } from './Interceptor/auth.interceptor';
import { CouponComponent } from './Component/coupon/coupon.component';
import { ChatComponent } from './Component/chat/chat.component';
import { MessagesComponent } from './Component/messages/messages.component';
import { StoreComponent } from './Component/store/store.component';
import { AssistanceComponent } from './Component/assistance/assistance.component';
import { FidelitycardComponent } from './Component/fidelitycard/fidelitycard.component';
import { ReviewComponent } from './Component/review/review.component';
import { UsersComponent } from './Component/users/users.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent,
    RegistrazioneComponent,
    LoginComponent,
    UserComponent,
    UsersComponent,
    CouponComponent,
    ChatComponent,
    MessagesComponent,
    StoreComponent,
    AssistanceComponent,
    FidelitycardComponent,
    ReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers:[AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
