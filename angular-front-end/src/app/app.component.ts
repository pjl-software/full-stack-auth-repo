import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { environment } from '../environment-configs/environment.local';
import { Observable, of } from 'rxjs';
import { ApplicationCoreSerivce } from './pjl-application/pjl-application-core/services/application-core.service';

@Component({
  selector: 'app-root',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'angular-front-end';
  environment = environment.environment;
  backEndHealthStatus$: Observable<string>;

  constructor(private applicationCoreSerivce: ApplicationCoreSerivce) {
    this.backEndHealthStatus$ = of('not yet set');
  }

  ngOnInit(): void {
    this.backEndHealthStatus$ = this.applicationCoreSerivce.getBackEndHealth();
  }
}