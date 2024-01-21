import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';

export const httpBasicAuthInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const username = 'user';
  const password = 'bb8a2513-a7f0-4a78-9a2d-c5dae2402880';
  const encodedBasicAuthToken = btoa(username + ':' + password);
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Basic ${encodedBasicAuthToken}`,
    },
  });
  return next(authReq);
};
