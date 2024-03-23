import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { map } from 'rxjs';
import { UserCoreSerivce } from '../services/pjl-authentication/user-core.service';

export const adminCanActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
) => {
  const router = inject(Router);

  return inject(UserCoreSerivce).currentUser$.pipe(
    map((user) => user.isAdmin),
    map((isAdmin) => isAdmin || router.createUrlTree(['/'])),
  );
};
