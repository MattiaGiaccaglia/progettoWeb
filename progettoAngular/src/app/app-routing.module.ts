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
import { MessageComponent } from './Component/message/message.component'; 
import { ChatComponent } from './Component/chat/chat.component';
import { ProfileComponent } from './Component/profile/profile.component';
import { ReviewsComponent } from './Component/reviews/reviews.component';
import { StoresComponent } from './Component/stores/stores.component';
import { MessagesComponent } from './Component/messages/messages.component';

const routes: Routes = [
  { path: "registrazione", component: RegistrazioneComponent },
  { path: "login", component: LoginComponent },
  { path: "profile", component: ProfileComponent},
  { path: "user", component: UsersComponent, children: [{path: ':id', component: UserComponent}]},  
  { path: "coupon", component: CouponComponent},
  { path: "assistance", component: AssistanceComponent},
  { path: "fidelitycard", component: FidelitycardComponent},
  { path: "review", component: ReviewComponent, children: [{path: ':id', component: ReviewsComponent}]},
  { path: "store", component: StoreComponent, children: [{path: ':id', component: StoresComponent}]},
  { path: "message", component: MessageComponent, children: [{path: ':id', component: MessagesComponent}]},
  { path: "chat", component: ChatComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
