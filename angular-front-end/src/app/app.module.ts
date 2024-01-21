import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
  provideHttpClient,
  withInterceptors,
} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PjlAuthenticationModule } from './pjl-authentication/pjl-authentication.module';
import { PjlDemoAModule } from './pjl-demo-a/pjl-demo-a.module';
import { PjlDemoBModule } from './pjl-demo-b/pjl-demo-b.module';
import { PjlSharedModule } from './pjl-shared/shared.module';

const httpBasicAuthInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const basicAuthToken = btoa('user:e3865b18-7ae9-48c1-a53a-2dce2b66e98f');
  console.log(basicAuthToken);
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Basic ${basicAuthToken}`,
    },
  });
  return next(authReq);
};

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    RouterModule.forRoot([]),

    PjlSharedModule,

    PjlDemoAModule,
    PjlDemoBModule,
    PjlAuthenticationModule,
  ],
  providers: [provideHttpClient(withInterceptors([httpBasicAuthInterceptor]))],
  bootstrap: [AppComponent],
})
export class AppModule {}
