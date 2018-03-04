import {ContextService} from '../context.service';
import {Component, OnInit} from '@angular/core';
import {Router, NavigationExtras} from '@angular/router';

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {
  message: string;

  constructor(public authService: ContextService, public router: Router) {
    this.setMessage();
  }

  setMessage() {
    this.message = (this.authService.isLoggedIn ? 'Connecté' : 'Déconnecté');
  }

  login(userName: string) {
    this.message = 'Tentative de connexion...' + userName;

    this.authService.login(userName).subscribe(() => {
      this.setMessage();
      if (this.authService.isLoggedIn) {
        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        const redirect = this.authService.redirectUrl && this.authService.redirectUrl !== '/' ? this.authService.redirectUrl : 'accueil';

        // Set our navigation extras
        // that passes on our global query params and fragment
        const navigationExtras: NavigationExtras = {
          queryParamsHandling: 'preserve',
          preserveFragment: true,
          replaceUrl: true
        };

        // Redirect the user
        this.router.navigate([redirect], navigationExtras);
      }
    });
  }

  logout() {
    const navigationExtras: NavigationExtras = {
      queryParamsHandling: 'preserve',
      preserveFragment: true,
      replaceUrl: true
    };

    // Redirect the user
    this.router.navigate(['logout'], navigationExtras);
  }

}
