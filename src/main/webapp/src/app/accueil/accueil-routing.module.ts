import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from '../auth.guard';
import {AccueilComponent} from './accueil/accueil.component';

const routes: Routes = [
  {path: 'accueil', component: AccueilComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccueilRoutingModule { }
