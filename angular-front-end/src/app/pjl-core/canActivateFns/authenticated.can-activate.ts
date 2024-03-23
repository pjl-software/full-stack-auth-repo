import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  RouterStateSnapshot,
} from '@angular/router';
import { map } from 'rxjs';
import { UserCoreSerivce } from '../services/pjl-authentication/user-core.service';

export const authenticatedCanActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
) => {
  return inject(UserCoreSerivce).currentUser$.pipe(
    map((user) => user.isAuthenticated),
    map((isAuthenticated) => isAuthenticated),
  );
};
