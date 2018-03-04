import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MatChipsModule} from '@angular/material/chips';

import {ConversationRoutingModule} from './conversation-routing.module';
import {DemoComponent} from './demo/demo.component';

import {
  MatInputModule,
  MatFormFieldModule,
  MatButtonModule
} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    ConversationRoutingModule,
    MatChipsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule
  ]
  , declarations: [DemoComponent]
})
export class ConversationModule {}
