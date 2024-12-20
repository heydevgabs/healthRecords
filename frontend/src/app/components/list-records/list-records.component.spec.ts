import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecordsComponent } from './list-records.component';

describe('ListRecordsComponent', () => {
  let component: ListRecordsComponent;
  let fixture: ComponentFixture<ListRecordsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListRecordsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListRecordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
