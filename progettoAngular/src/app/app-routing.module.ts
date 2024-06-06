import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrazioneComponent } from './Component/registrazione/registrazione.component';
import { LoginComponent } from './Component/login/login.component';
import { UserComponent } from './Component/User/user.component';
import { UsersComponent } from './Component/users/users.component';
import { CouponComponent } from './Component/coupon/coupon.component';
import { AssistanceComponent } from './Component/assistance/assistance.component'; 
import { FidelitycardComponent } from './Component/fidelitycard/fidelitycard.component';
import { ReviewComponent } from './Component/review/review.component'; 
import { StoreComponent } from './Component/store/store.component'; 
import { MessagesComponent } from './Component/messages/messages.component'; 
import { ChatComponent } from './Component/chat/chat.component';

const routes: Routes = [
  { path: "registrazione", component: RegistrazioneComponent },
  { path: "login", component: LoginComponent },
  {path: 'user', component: UsersComponent, children: [{path: ':id', component: UserComponent}]},  { path: "coupon", component: CouponComponent},
  { path: "assistenza", component: AssistanceComponent},
  { path: "fidelitycard", component: FidelitycardComponent},
  { path: "recensioni", component: ReviewComponent},
  { path: "negozi", component: StoreComponent},
  { path: "messaggi", component: MessagesComponent},
  { path: "chat", component: ChatComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
