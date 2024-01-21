import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Observable, of, switchMap, timer } from 'rxjs';
import { ApplicationCoreSerivce } from '../../../pjl-core/services/pjl-application/application-core.service';

@Component({
  selector: 'app-demo-b',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './demo-b.component.html',
  styleUrl: './demo-b.component.scss',
})
export class DemoBComponent {
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
