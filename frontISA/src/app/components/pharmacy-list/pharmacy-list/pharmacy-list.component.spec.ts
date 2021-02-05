import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyListComponent } from './pharmacy-list.component';

describe('PharmacyListComponent', () => {
  let component: PharmacyListComponent;
  let fixture: ComponentFixture<PharmacyListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharmacyListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
