import { Routes } from '@angular/router';
import { AuthenticationDashboardComponent } from './pjl-authentication/pjl-authentication-components/authentication-dashboard/authentication-dashboard.component';
import { CreateUserButtonComponent } from './pjl-authentication/pjl-authentication-components/create-user-button/create-user-button.component';
import { DeleteUserButtonComponent } from './pjl-authentication/pjl-authentication-components/delete-user-button/delete-user-button.component';
import { ViewUsersComponent } from './pjl-authentication/pjl-authentication-components/view-users/view-users.component';

export const routes: Routes = [
  {
    path: 'create-user',
    component: CreateUserButtonComponent,
  },
  {
    path: 'delete-user',
    component: DeleteUserButtonComponent,
  },
  { path: '', component: AuthenticationDashboardComponent },
  {
    path: 'view-users',
    component: ViewUsersComponent,
  },
];
