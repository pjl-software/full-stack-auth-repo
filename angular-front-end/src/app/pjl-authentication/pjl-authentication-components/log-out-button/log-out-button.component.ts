import { ChangeDetectionStrategy, Component } from '@angular/core';
import { UserCoreSerivce } from '../../../pjl-core/services/pjl-authentication/user-core.service';
import { PjlSharedModule } from '../../../pjl-shared/shared.module';

@Component({
  selector: 'app-log-out-button',
  standalone: true,
  imports: [PjlSharedModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './log-out-button.component.html',
  styleUrl: './log-out-button.component.scss',
})
export class LogOutButtonComponent {
  constructor(private userService: UserCoreSerivce) {}

  logOutUser() {
    this.userService.signOut();
  }
}
