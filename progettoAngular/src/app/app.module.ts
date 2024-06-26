import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrazioneComponent } from './Component/Registrazione/registrazione.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Component/Login/login.component';
import { UserComponent } from './Component/User/user.component';
import { AuthInterceptorProvider } from './Interceptor/auth.interceptor';
import { CouponComponent } from './Component/coupon/coupon.component';
import { ChatComponent } from './Component/chat/chat.component';
import { MessageComponent } from './Component/message/message.component';
import { StoreComponent } from './Component/store/store.component';
import { AssistanceComponent } from './Component/assistance/assistance.component';
import { FidelitycardComponent } from './Component/fidelitycard/fidelitycard.component';
import { ReviewComponent } from './Component/review/review.component';
import { UsersComponent } from './Component/users/users.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './Component/profile/profile.component';
import { ReviewsComponent } from './Component/reviews/reviews.component';
import { StoresComponent } from './Component/stores/stores.component';
import { MessagesComponent } from './Component/messages/messages.component';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { ChatsComponent } from './Component/chats/chats.component';
import {MatTooltipModule} from '@angular/material/tooltip';


@NgModule({
  declarations: [
    AppComponent,
    RegistrazioneComponent,
    LoginComponent,
    UserComponent,
    UsersComponent,
    ProfileComponent,
    CouponComponent,
    ChatComponent,
    MessageComponent,
    StoreComponent,
    AssistanceComponent,
    FidelitycardComponent,
    ReviewComponent,
    ProfileComponent,
    ReviewsComponent,
    StoresComponent,
    MessagesComponent,
    ChatsComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatTableModule,
    MatCardModule,
    MatTooltipModule
  ],
  providers:[AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
