import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserCoreSerivce } from './pjl-core/services/pjl-authentication/user-core.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  constructor(public userService: UserCoreSerivce) {}

  ngOnInit(): void {
    this.userService.initalizeUserWithAppLoading();
    return;
  }
}
