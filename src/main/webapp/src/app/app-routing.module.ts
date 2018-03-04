import {AppComponent} from './app.component';
import {AuthGuard} from './auth.guard';
import {DemoComponent} from './conversation/demo/demo.component';
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
  {path: '', component: AppComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
