import { SocialAuthService } from '@abacritt/angularx-social-login';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, distinctUntilChanged, map } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import { BackEndAuthenticatedUserProjection } from '../../../pjl-authentication/pjl-authentication-models/back-end/back-end-authenticated-user-projection.model';
import { JwtService } from './jwt.service';

@Injectable({ providedIn: 'root' })
export class UserCoreSerivce {
  //
  // Subjects
  //

  private currentUserSubject =
    new BehaviorSubject<BackEndAuthenticatedUserProjection>({
      isAuthenticated: false,
      isNotAuthenticated: true,
    } as BackEndAuthenticatedUserProjection);
  public currentUser$ = this.currentUserSubject
    .asObservable()
    .pipe(distinctUntilChanged());

  //
  // Constructor
  //

  constructor(
    private jwtService: JwtService,
    private http: HttpClient,
    private authService: SocialAuthService
  ) {}

  //
  // Public Functions
  //

  /**
   * Name stinks. Rethink naming.
   *
   * @returns
   */
  initalizeUserWithAppLoading(): void {
    const existingToken = this.jwtService.getToken();
    if (existingToken) {
      this.getUserInfo().subscribe({
        next: (user: BackEndAuthenticatedUserProjection) => {
          if (user) {
            this.setUser(user);
          } else {
            this.purgeUser();
          }
        },
        error: (error) => {
          this.signOut();
        },
      });
    } else {
      this.signOut();
    }
    return;
  }

  updateCurrentUser(
    backEndUserDto: BackEndAuthenticatedUserProjection
  ): BackEndAuthenticatedUserProjection {
    this.currentUserSubject.next(backEndUserDto);
    return { ...backEndUserDto };
  }

  signOut(): void {
    this.purgeUser();
    this.authService.signOut();
  }

  //
  // Private Functions
  //

  /**
   *
   * @returns - Observable<BackEndUserDto>; otherwise HttpError
   */
  private getUserInfo(): Observable<BackEndAuthenticatedUserProjection> {
    return this.http
      .get(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.getUserInformation}`,
        {}
      )
      .pipe(
        map<any, BackEndAuthenticatedUserProjection>((response) => {
          const backEndUserDtos: BackEndAuthenticatedUserProjection = response;
          return backEndUserDtos;
        })
      );
  }

  private purgeUser(): void {
    this.jwtService.destroyToken();
    this.currentUserSubject.next({
      isAuthenticated: false,
      isNotAuthenticated: true,
    } as BackEndAuthenticatedUserProjection);
    return;
  }

  private setUser(user: BackEndAuthenticatedUserProjection): void {
    this.currentUserSubject.next(user);
    return;
  }
}
