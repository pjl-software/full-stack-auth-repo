import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';
import { UserDto } from '../../../pjl-authentication/pjl-authentication-models/user-dto.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationCoreSerivce {
  constructor(private http: HttpClient) {}

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
