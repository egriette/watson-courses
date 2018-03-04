import {ChatService} from '../chat.service';
import {ContextService} from '../../context.service';

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {QueryList} from '@angular/core';
import {MatChipInputEvent, MatChipList, MatChip} from '@angular/material';
import {ChatResponse} from '../ChatResponse';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css'],
  providers: [ChatService]
})
export class DemoComponent implements OnInit {

  classCL: string;
  orientationCL: string;

  information: any[] = [];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private chatService: ChatService, private contexteService: ContextService) {}

  ngOnInit() {
    this.classCL = 'mat-chip-list-stacked';
    this.orientationCL = 'vertical';
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.information.push({source: value.trim(), color: 'primary'});
      this.chatService.sendMessage({source : value}, this.contexteService.userName()).subscribe(msg => this.addResponse(msg));
    }
    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  addResponse(msgs : ChatResponse) {
    for (const msg of msgs.source) {
      this.information.push({source: msg.trim(), color: 'accent'});
    }
  }

  clearData(chipList : MatChipList) {
    this.information = [];
    chipList.chips = new QueryList<MatChip>();
    this.chatService.sendMessage({source : 'Reset'}, this.contexteService.userName()).subscribe(msg => this.addResponse(msg));
  }

}
