import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApplicationCoreSerivce {
  constructor(private http: HttpClient) {}

  /**
   *
   * @returns Observable string indicating the health of the back-end server utilizing the /actuator URL that comes with the
   * Spring Boot Starter Actuator library
   */
  getBackEndHealth(): Observable<string> {
    return this.http.get('https://localhost:8443/actuator/health', {}).pipe(
      map<any, string>((response) => {
        return response.status;
      }),
      catchError((err: HttpErrorResponse) => {
        return of('Actuator Not Healthy!');
      })
    );
  }
}
