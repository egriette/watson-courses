import { Component, OnInit } from '@angular/core';
import {Texte} from '../../shared/Texte';

@Component({
  selector: 'app-text-to-speech',
  templateUrl: './text-to-speech.component.html',
  styleUrls: ['./text-to-speech.component.css']
})
export class TextToSpeechComponent {

  listOfText: Texte[] = [];

  constructor() { }

  addTexte(text: string) {
    this.listOfText.push({source : text, dataUrl: ''});
  }

}
