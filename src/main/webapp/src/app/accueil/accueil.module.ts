import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccueilRoutingModule } from './accueil-routing.module';
import { AccueilComponent } from './accueil/accueil.component';

@NgModule({
  imports: [
    CommonModule,
    AccueilRoutingModule
  ],
  declarations: [AccueilComponent]
})
export class AccueilModule { }
