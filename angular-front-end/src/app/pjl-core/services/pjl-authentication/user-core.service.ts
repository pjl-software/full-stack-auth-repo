import { SocialAuthService } from '@abacritt/angularx-social-login';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, distinctUntilChanged, map } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import {
  BackEndAuthenticatedUserProjection,
  UnauthenticatedBackEndAuthenticatedUserProjection,
} from '../../../pjl-authentication/pjl-authentication-models/back-end/back-end-authenticated-user-projection.model';
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
    private socialAuthService: SocialAuthService
  ) {}

  //
  // Public Functions
  //

  /**
   * Load an authenticated user's information with a valid JWT; otherwise sign out any remnants of a user.
   *
   * If there is an existing token in storage, try using it to get user information; otherwise signOut() the user.
   *
   * @returns - populated BackEndAuthenticatedUserProjection; otherwise unauthenticated BackEndAuthenticatedUserProjection
   */
  initalizeUser(): BackEndAuthenticatedUserProjection {
    const existingToken = this.jwtService.getToken();
    if (existingToken) {
      this.getUserInfo().subscribe({
        next: (user: BackEndAuthenticatedUserProjection) => {
          if (user) {
            return this.updateCurrentUser(user);
          } else {
            this.purgeUser();
            return UnauthenticatedBackEndAuthenticatedUserProjection;
          }
        },
        error: (error) => {
          this.signOut();
        },
      });
    } else {
      this.signOut();
    }
    return UnauthenticatedBackEndAuthenticatedUserProjection;
  }

  /**
   * Update the currentUserSubject to the value passed in.
   *
   * @param backEndUserDto - New value for currentUserSubject
   * @returns - a copy of the backEndUserDto
   */
  updateCurrentUser(
    backEndUserDto: BackEndAuthenticatedUserProjection
  ): BackEndAuthenticatedUserProjection {
    this.currentUserSubject.next(backEndUserDto);
    return { ...backEndUserDto };
  }

  /**
   * Purge the user and sign out from the SocialAuthService library.
   */
  signOut(): void {
    this.purgeUser();
    this.socialAuthService.signOut();
    return;
  }

  //
  // Private Functions
  //

  /**
   * Try to get a user's information using the JWT in the request.
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

  /**
   * Destroy the JWT from local storage. Update currentUserSubject to unauthenticated BackEndAuthenticatedUserProjection
   *
   * @returns
   */
  private purgeUser(): void {
    this.jwtService.destroyToken();
    this.currentUserSubject.next(
      UnauthenticatedBackEndAuthenticatedUserProjection
    );
    return;
  }
}
