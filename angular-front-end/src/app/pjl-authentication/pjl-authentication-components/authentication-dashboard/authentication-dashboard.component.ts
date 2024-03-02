import {
  GoogleSigninButtonModule,
  SocialAuthService,
} from '@abacritt/angularx-social-login';
import {
  ChangeDetectionStrategy,
  Component,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthenticationCoreSerivce } from '../../../pjl-core/services/pjl-authentication/authentication-core.service';
import { JwtService } from '../../../pjl-core/services/pjl-authentication/jwt.service';
import { UserCoreSerivce } from '../../../pjl-core/services/pjl-authentication/user-core.service';
import { PjlSharedModule } from '../../../pjl-shared/shared.module';
import { CreateUserButtonComponent } from '../create-user-button/create-user-button.component';
import { DeleteUserButtonComponent } from '../delete-user-button/delete-user-button.component';
import { LogOutButtonComponent } from '../log-out-button/log-out-button.component';
import { ViewUsersComponent } from '../view-users/view-users.component';

@Component({
  selector: 'app-authentication-dashboard',
  standalone: true,
  imports: [
    CreateUserButtonComponent,
    DeleteUserButtonComponent,
    ViewUsersComponent,
    PjlSharedModule,
    GoogleSigninButtonModule,
    LogOutButtonComponent,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './authentication-dashboard.component.html',
  styleUrl: './authentication-dashboard.component.scss',
})
export class AuthenticationDashboardComponent implements OnInit, OnDestroy {
  createGoogleUserSubscription: Subscription = new Subscription();
  loggedIn: boolean = false;

  constructor(
    private authService: SocialAuthService,
    private jwtService: JwtService,
    private authenticationCoreSerivce: AuthenticationCoreSerivce,
    public userService: UserCoreSerivce
  ) {}

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      this.loggedIn = user != null;
      if (user != null) {
        this.jwtService.saveToken(user.idToken);
        this.createGoogleUserSubscription = this.authenticationCoreSerivce
          .createGoogleUser()
          .subscribe({
            next: (user) => {
              // need to manually subscribe
            },
          });
      }
    });
  }

  ngOnDestroy(): void {
    this.createGoogleUserSubscription.unsubscribe();
  }
}
