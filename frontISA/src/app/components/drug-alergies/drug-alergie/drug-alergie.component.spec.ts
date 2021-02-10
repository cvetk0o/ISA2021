import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrugAlergieComponent } from './drug-alergie.component';

describe('DrugAlergieComponent', () => {
  let component: DrugAlergieComponent;
  let fixture: ComponentFixture<DrugAlergieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrugAlergieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugAlergieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
