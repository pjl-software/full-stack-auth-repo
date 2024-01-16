import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticationDashboardComponent } from './authentication-dashboard.component';

describe('AuthenticationDashboardComponent', () => {
  let component: AuthenticationDashboardComponent;
  let fixture: ComponentFixture<AuthenticationDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthenticationDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AuthenticationDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
