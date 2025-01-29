import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/component/register/register.component';
import { LoginComponent } from './auth/component/login/login.component';

const routes: Routes = [
  {path:'register',component:RegisterComponent},
  {path:'',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
