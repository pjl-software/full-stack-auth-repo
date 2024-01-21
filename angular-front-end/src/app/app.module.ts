import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { PjlAuthenticationModule } from './pjl-authentication/pjl-authentication.module';
import { PjlDemoAModule } from './pjl-demo-a/pjl-demo-a.module';
import { PjlDemoBModule } from './pjl-demo-b/pjl-demo-b.module';
import { PjlSharedModule } from './pjl-shared/shared.module';

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
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
