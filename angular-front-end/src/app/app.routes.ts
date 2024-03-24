import { Routes } from '@angular/router';
import { AdminDashboardComponent } from './pjl-authentication/pjl-authentication-components/admin-dashboard/admin-dashboard.component';
import { AuthenticationDashboardComponent } from './pjl-authentication/pjl-authentication-components/authentication-dashboard/authentication-dashboard.component';
import { CreateUserButtonComponent } from './pjl-authentication/pjl-authentication-components/create-user-button/create-user-button.component';
import { DeleteUserButtonComponent } from './pjl-authentication/pjl-authentication-components/delete-user-button/delete-user-button.component';
import { ViewUsersComponent } from './pjl-authentication/pjl-authentication-components/view-users/view-users.component';
import { adminCanActivate } from './pjl-core/canActivateFns/admin.can-activate';

export const routes: Routes = [
  {
    path: 'create-user',
    component: CreateUserButtonComponent,
  },
  {
    path: 'delete-user',
    component: DeleteUserButtonComponent,
  },
  {
    path: 'view-users',
    component: ViewUsersComponent,
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [adminCanActivate],
  },
  {
    path: '',
    component: AuthenticationDashboardComponent,
  },
];
