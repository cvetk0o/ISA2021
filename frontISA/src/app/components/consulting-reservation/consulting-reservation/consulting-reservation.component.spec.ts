import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultingReservationComponent } from './consulting-reservation.component';

describe('ConsultingReservationComponent', () => {
  let component: ConsultingReservationComponent;
  let fixture: ComponentFixture<ConsultingReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultingReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultingReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
