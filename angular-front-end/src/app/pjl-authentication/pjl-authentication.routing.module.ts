import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewUsersComponent } from './pjl-authentication-components/view-users/view-users.component';

const routes: Routes = [
  {
    path: 'view-users',
    component: ViewUsersComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RoutingModuleAuthentication {}
