import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import { UserDto } from '../../../pjl-authentication/pjl-authentication-models/user-dto.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationCoreSerivce {
  constructor(private http: HttpClient) {}

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
