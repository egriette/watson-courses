import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

@Injectable()
export class ContextService {
  isLoggedIn = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  login(userName: string): Observable<boolean> {
    return Observable.of(true).delay(1000).do(val => {
      localStorage.setItem('user', userName);
      this.isLoggedIn = true;
    });
  }

  logout(): Observable<boolean> {
    return Observable.of(true).delay(1000).do(val => {
      localStorage.removeItem('user');
      this.isLoggedIn = false;
    });
  }

  userName() : string {
    return localStorage.getItem('user');
  }

}
