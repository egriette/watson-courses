import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ContextService} from '../../context.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  constructor(public authService: ContextService, public router: Router) {
  }

  ngOnInit() {
  }

}
