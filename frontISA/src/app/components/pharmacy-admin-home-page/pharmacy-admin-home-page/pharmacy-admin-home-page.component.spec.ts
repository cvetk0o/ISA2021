import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyAdminHomePageComponent } from './pharmacy-admin-home-page.component';

describe('PharmacyAdminHomePageComponent', () => {
  let component: PharmacyAdminHomePageComponent;
  let fixture: ComponentFixture<PharmacyAdminHomePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharmacyAdminHomePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyAdminHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
