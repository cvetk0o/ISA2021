import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinishedStuffComponent } from './finished-stuff.component';

describe('FinishedStuffComponent', () => {
  let component: FinishedStuffComponent;
  let fixture: ComponentFixture<FinishedStuffComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinishedStuffComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinishedStuffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
