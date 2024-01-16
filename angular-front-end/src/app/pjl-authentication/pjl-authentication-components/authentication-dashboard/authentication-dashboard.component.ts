import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-authentication-dashboard',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './authentication-dashboard.component.html',
  styleUrl: './authentication-dashboard.component.scss',
})
export class AuthenticationDashboardComponent {}
