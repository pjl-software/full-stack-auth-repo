import { Component } from '@angular/core';
import { environment } from '../environment-configs/environment.local';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'angular-front-end';
  environment = environment.environment;
}
