import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoBComponent } from './demo-b.component';

describe('DemoBComponent', () => {
  let component: DemoBComponent;
  let fixture: ComponentFixture<DemoBComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemoBComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DemoBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
