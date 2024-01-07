import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Observable, of, switchMap, timer } from 'rxjs';
import { ApplicationCoreSerivce } from '../../../pjl-core/services/application-core.service';

@Component({
  selector: 'app-demo-a',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './demo-a.component.html',
  styleUrl: './demo-a.component.scss',
})
export class DemoAComponent implements OnInit {
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
