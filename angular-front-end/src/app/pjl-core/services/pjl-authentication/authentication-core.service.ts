import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import {
  BackEndAuthenticatedUserProjection,
  UnauthenticatedBackEndAuthenticatedUserProjection,
} from '../../../pjl-authentication/pjl-authentication-models/back-end/back-end-authenticated-user-projection.model';
import { BackEndGenericUserProjection } from '../../../pjl-authentication/pjl-authentication-models/back-end/back-end-generic-user-projection-dto.model';
import { UserCoreSerivce } from './user-core.service';

@Injectable({ providedIn: 'root' })
export class AuthenticationCoreSerivce {
  constructor(private http: HttpClient, private userService: UserCoreSerivce) {}

  /**
   * Create, log-in, or re-enable, a user in our database who used Google Sign-on
   *
   * @returns - An Observable<string> indicating if the user was created.
   */
  createGoogleUser(): Observable<BackEndAuthenticatedUserProjection> {
    return this.http
      .post(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.AuthController.createGoogleUser}`,
        {}
      )
      .pipe(
        map<any, BackEndAuthenticatedUserProjection>(
          (user: BackEndAuthenticatedUserProjection) => {
            this.userService.updateCurrentUser(user);
            return user;
          }
        ),
        catchError((err: HttpErrorResponse) => {
          return of(UnauthenticatedBackEndAuthenticatedUserProjection);
        })
      );
  }

  /**
   * Create a new user in our database.
   *
   * @returns - An Observable<string> indicating if the user was created.
   */
  createUser(): Observable<string> {
    return this.http
      .post(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.createUser}`,
        {}
      )
      .pipe(
        map<any, string>((response) => {
          return response.value;
        }),
        catchError((err: HttpErrorResponse) => {
          if (err.status === 401) {
            return of(
              `You are not authorized to perform this action. Check you're authenticated.`
            );
          } else if (err.status === 403) {
            return of(
              'You do not have the appropriate roles to perform this action.'
            );
          }
          return of('Failed to create new user');
        })
      );
  }

  /**
   * Delete a user from our databse.
   *
   * @param username - Username value indicating the user we want to delete.
   * @returns - An Observable<string> indicating if the user was deleted.
   */
  deleteUser(username: string): Observable<string> {
    const path: string =
      `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.deleteUser}/` +
      username;

    return this.http.put(path, {}).pipe(
      map<any, string>((response) => {
        return response.value;
      }),
      catchError((err: HttpErrorResponse) => {
        if (err.status === 400) {
          return of(
            'Unable to delete that user. Are you sure you used the correct username?'
          );
        } else if (err.status === 401) {
          return of(
            `You are not authorized to perform this action. Check you're authenticated.`
          );
        } else if (err.status === 403) {
          return of(
            'You do not have the appropriate roles to perform this action.'
          );
        }
        return of('Failed to delete user.');
      })
    );
  }

  /**
   * Get a list of enabled users from the database.
   *
   * @returns - An Observable list of UserDto objects representing all the users in the database where enabled is true.
   */
  getEnabledUsers(): Observable<BackEndGenericUserProjection[]> {
    return this.http
      .get(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.getEnabledUsers}`,
        {}
      )
      .pipe(
        map<any, BackEndGenericUserProjection[]>((response) => {
          const backEndUserDtos: BackEndGenericUserProjection[] = response;
          return backEndUserDtos;
        }),
        catchError((err: HttpErrorResponse) => {
          return of([]);
        })
      );
  }
}
