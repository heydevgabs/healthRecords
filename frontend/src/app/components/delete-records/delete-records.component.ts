import { Component } from '@angular/core';
import { RecordService } from '../../services/record.service';
import { NgIf } from '@angular/common';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {MatButton} from '@angular/material/button';
import {MatDivider} from '@angular/material/divider';

@Component({
  selector: 'app-delete-records',
  standalone: true,
  imports: [
    NgIf,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatButton,
    MatDivider
  ],
  templateUrl: './delete-records.component.html',
  styleUrls: ['./delete-records.component.scss']
})
export class DeleteRecordsComponent {
  message: string = '';

  constructor(private recordService: RecordService) {}

  deleteRecords(): void {
    const confirmDelete = window.confirm('Are you sure you want to delete all records?');
    if (confirmDelete) {
      this.recordService.deleteAllRecords().subscribe({
        next: (response) => (this.message = response),
        error: (err) => (this.message = `Error: ${err}`),
      });
    }
  }
}
