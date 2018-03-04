import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpeechRoutingModule } from './speech-routing.module';
import { TextToSpeechComponent } from './text-to-speech/text-to-speech.component';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule } from '@angular/material';
import { MatListModule } from '@angular/material/list';
import { ListComponent } from './list/list.component';
import { TextToSpeechService } from './text-to-speech.service';

@NgModule({
  imports: [
    CommonModule,
    SpeechRoutingModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatListModule
  ],
  declarations: [TextToSpeechComponent, ListComponent],
  providers: [TextToSpeechService]
})
export class SpeechModule { }
