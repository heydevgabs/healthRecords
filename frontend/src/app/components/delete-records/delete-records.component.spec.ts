import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteRecordsComponent } from './delete-records.component';

describe('DeleteRecordsComponent', () => {
  let component: DeleteRecordsComponent;
  let fixture: ComponentFixture<DeleteRecordsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteRecordsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteRecordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
