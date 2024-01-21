import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AuthenticationCoreSerivce } from '../../../pjl-core/services/pjl-authentication/authentication-core.service';
import { PjlSharedModule } from '../../../pjl-shared/shared.module';

@Component({
  selector: 'app-delete-user-button',
  standalone: true,
  imports: [PjlSharedModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './delete-user-button.component.html',
  styleUrl: './delete-user-button.component.scss',
})
export class DeleteUserButtonComponent {
  deleteUserResponse$: Observable<string>;
  usernameInput: string;

  constructor(private authenticationCoreSerivce: AuthenticationCoreSerivce) {
    this.deleteUserResponse$ = of();
    this.usernameInput = '';
  }

  deleteUser() {
    this.deleteUserResponse$ = this.authenticationCoreSerivce.deleteUser(
      this.usernameInput
    );
  }
}
