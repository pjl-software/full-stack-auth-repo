import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { JwtService } from '../services/pjl-authentication/jwt.service';

interface HeadersConfig {
  [key: string]: string;
}

export const jwtTokenAuthInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const jwtService = inject(JwtService);
  const headersConfig: HeadersConfig = {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  };

  const token = jwtService.getToken();

  if (token) {
    headersConfig['Authorization'] = `Bearer ${token}`;
  }

  const request = req.clone({ setHeaders: headersConfig });
  return next(request);
};
