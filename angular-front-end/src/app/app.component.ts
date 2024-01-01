import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { environment } from '../environment-configs/environment.local';
import { Observable, interval, of, startWith, switchMap, timer } from 'rxjs';
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
    const autoRefreshRateInMilliSeconds: number = 5000; // 5 seconds
    this.backEndHealthStatus$ = timer(0, autoRefreshRateInMilliSeconds).pipe(
      switchMap(() => this.applicationCoreSerivce.getBackEndHealth())
    );
  }
}
