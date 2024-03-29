import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import {
  GoogleInitOptions,
  GoogleLoginProvider,
  SocialAuthServiceConfig,
} from '@abacritt/angularx-social-login';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { jwtTokenAuthInterceptor } from './pjl-core/interceptors/jwt-token-auth.interceptor';

const googleInitOptions: GoogleInitOptions = {
  oneTapEnabled: false,
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptors([jwtTokenAuthInterceptor])),
    provideRouter(routes),
    [
      {
        provide: 'SocialAuthServiceConfig',
        useValue: {
          autoLogin: false,
          providers: [
            {
              id: GoogleLoginProvider.PROVIDER_ID,
              provider: new GoogleLoginProvider(
                '603021351518-d3kr8eie71dcfei6ud9531kmp8bbhqq3.apps.googleusercontent.com',
                googleInitOptions
              ),
            },
          ],
          onError: (err: any) => {
            console.error(err);
          },
        } as SocialAuthServiceConfig,
      },
    ],
  ],
};
