import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Texte } from '../../shared/Texte';
import {TextToSpeechService} from '../text-to-speech.service';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
    styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

    @Input() list: Texte[];

    constructor(private service: TextToSpeechService, private domSanitizer: DomSanitizer) { }

    ngOnInit() {
    }

    play(text: Texte) {
        this.service.play(text.source).subscribe(resp => {
            text.dataUrl = this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(resp));
            const audio = new Audio();
            audio.src = (<string> text.dataUrl);
            audio.play();
        });
    }

}
