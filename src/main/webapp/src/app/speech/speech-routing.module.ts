import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TextToSpeechComponent} from './text-to-speech/text-to-speech.component';
import {AuthGuard} from '../auth.guard';

const routes: Routes = [
  {path: 'speech', component: TextToSpeechComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpeechRoutingModule { }
