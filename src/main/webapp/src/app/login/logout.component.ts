import {ContextService} from '../context.service';
import {Component, OnInit} from '@angular/core';
import {NavigationExtras, Router} from '@angular/router';

@Component({
  templateUrl: './login.component.html'
})
export class LogoutComponent implements OnInit {
  message: string;

  constructor(public authService: ContextService, public router: Router) {
  }

  ngOnInit() {
    this.authService.logout().subscribe(() => {
      const navigationExtras: NavigationExtras = {
        queryParamsHandling: 'preserve',
        preserveFragment: true,
        replaceUrl: true
      };

      // Redirect the user
      this.router.navigate(['login'], navigationExtras);
    });
  }

}
