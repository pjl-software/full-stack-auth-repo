import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment-configs/environment.local';

@Injectable({
  providedIn: 'root',
})
export class ApplicationCoreSerivce {
  constructor(private http: HttpClient) {}

  /**
   *
   * @returns Observable string indicating the health of the back-end server
   */
  getBackEndHealth(): Observable<string> {
    return this.http.get(`${environment.apiUrl}` + '/health', {}).pipe(
      map<any, string>((response) => {
        return response.status;
      }),
      catchError((err: HttpErrorResponse) => {
        return of('Not Healthy!');
      })
    );
  }
}
