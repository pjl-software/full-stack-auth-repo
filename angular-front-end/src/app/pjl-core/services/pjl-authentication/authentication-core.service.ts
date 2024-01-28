import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import { UserDto } from '../../../pjl-authentication/pjl-authentication-models/user-dto.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationCoreSerivce {
  constructor(private http: HttpClient) {}

  /**
   * Create, or re-enable, a user in our database who used Google Sign-on
   *
   * @returns - An Observable<string> indicating if the user was created.
   */
  createGoogleUser(): Observable<string> {
    return this.http
      .post(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.createGoogleUser}`,
        {}
      )
      .pipe(
        map<any, string>((response) => {
          return response.value;
        }),
        catchError((err: HttpErrorResponse) => {
          return of('Failed to create new user');
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
        return of('Failed to delete user.');
      })
    );
  }

  /**
   * Get a list of enabled users from the database.
   *
   * @returns - An Observable list of UserDto objects representing all the users in the database where enabled is true.
   */
  getEnabledUsers(): Observable<UserDto[]> {
    return this.http
      .get(
        `${environment.apiUrl}${environment.apiVersion}${environment.backEndControllerPaths.UserController.getEnabledUsers}`,
        {}
      )
      .pipe(
        map<any, UserDto[]>((response) => {
          return response;
        }),
        catchError((err: HttpErrorResponse) => {
          return of([]);
        })
      );
  }
}
