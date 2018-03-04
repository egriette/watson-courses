import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoginRoutingModule} from './login.routing.module';
import {LogoutComponent} from './logout.component';
import {LoginComponent} from './login.component';

import {
  MatInputModule,
  MatFormFieldModule,
  MatButtonModule
} from '@angular/material';


@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule
  ]
  , declarations: [LoginComponent, LogoutComponent]
})
export class LoginModule {}
