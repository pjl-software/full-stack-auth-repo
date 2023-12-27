import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RouterOutlet } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, RouterOutlet, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
