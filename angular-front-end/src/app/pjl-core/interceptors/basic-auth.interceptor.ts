import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';

export const httpBasicAuthInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const username = 'pjl';
  const password = 'abc123';
  const encodedBasicAuthToken = btoa(username + ':' + password);
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Basic ${encodedBasicAuthToken}`,
    },
  });
  return next(authReq);
};
