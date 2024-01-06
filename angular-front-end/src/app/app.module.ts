import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
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
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
