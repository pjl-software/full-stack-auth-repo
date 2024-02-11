import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Observable, of, switchMap, timer } from 'rxjs';
import { AuthenticationCoreSerivce } from '../../../pjl-core/services/pjl-authentication/authentication-core.service';
import { PjlSharedModule } from '../../../pjl-shared/shared.module';
import { BackEndGenericUserProjection } from '../../pjl-authentication-models/back-end/back-end-generic-user-projection-dto.model';

@Component({
  selector: 'app-view-users',
  standalone: true,
  imports: [PjlSharedModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './view-users.component.html',
  styleUrl: './view-users.component.scss',
})
export class ViewUsersComponent implements OnInit {
  enabledUsers$: Observable<BackEndGenericUserProjection[]>;

  constructor(private authenticationCoreSerivce: AuthenticationCoreSerivce) {
    this.enabledUsers$ = of([]);
  }

  ngOnInit(): void {
    const autoRefreshRateInMilliSeconds: number = 10000;
    this.enabledUsers$ = timer(0, autoRefreshRateInMilliSeconds).pipe(
      switchMap(() => this.authenticationCoreSerivce.getEnabledUsers())
    );
  }
}
