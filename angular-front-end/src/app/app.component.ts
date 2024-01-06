import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Observable, of, switchMap, timer } from 'rxjs';
import { environment } from '../environment-configs/environment.local';
import { ApplicationCoreSerivce } from './pjl-core/services/application-core.service';

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
