import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AuthenticationCoreSerivce } from '../../../pjl-core/services/pjl-authentication/authentication-core.service';
import { PjlSharedModule } from '../../../pjl-shared/shared.module';

@Component({
  selector: 'app-create-user-button',
  standalone: true,
  imports: [PjlSharedModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './create-user-button.component.html',
  styleUrl: './create-user-button.component.scss',
})
export class CreateUserButtonComponent {
  createUserResult$: Observable<string>;
  constructor(private authenticationCoreSerivce: AuthenticationCoreSerivce) {
    this.createUserResult$ = of('');
  }

  createNewUser() {
    this.createUserResult$ = this.authenticationCoreSerivce.createUser();
  }
}
