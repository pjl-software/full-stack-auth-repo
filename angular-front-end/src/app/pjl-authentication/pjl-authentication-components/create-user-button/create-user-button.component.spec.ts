import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateUserButtonComponent } from './create-user-button.component';

describe('CreateUserButtonComponent', () => {
  let component: CreateUserButtonComponent;
  let fixture: ComponentFixture<CreateUserButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateUserButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateUserButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
