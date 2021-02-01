import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrugReservationComponent } from './drug-reservation.component';

describe('DrugReservationComponent', () => {
  let component: DrugReservationComponent;
  let fixture: ComponentFixture<DrugReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrugReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
