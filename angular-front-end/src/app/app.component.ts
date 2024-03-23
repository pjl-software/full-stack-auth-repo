import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { CoreLayoutComponent } from './pjl-common/core-layout/core-layout.component';
import { UserCoreSerivce } from './pjl-core/services/pjl-authentication/user-core.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CoreLayoutComponent],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  constructor(public userService: UserCoreSerivce) {}

  ngOnInit(): void {
    this.userService.initalizeUser();
    return;
  }
}
