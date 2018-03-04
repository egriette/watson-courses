import {ContextService} from './context.service';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatMenuTrigger} from '@angular/material/menu';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Chat avec Watson';

  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  constructor(public authService: ContextService) {
  }

  ngOnInit() {
  }

}
