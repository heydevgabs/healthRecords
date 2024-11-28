import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { RecordService, Record } from '../../services/record.service';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatError} from '@angular/material/form-field';

@Component({
  selector: 'app-list-records',
  standalone: true,
  imports: [
    NgIf,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatTable,
    MatHeaderCell,
    MatColumnDef,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatCellDef,
    MatHeaderCellDef,
    MatError
  ],
  templateUrl: './list-records.component.html',
  styleUrls: ['./list-records.component.scss']
})
export class ListRecordsComponent implements OnInit {
  records: Record[] = [];
  errorMessage: string = '';
  isLoading: boolean = true;
  displayedColumns: string[] = [
    'source',
    'codeListCode',
    'code',
    'displayValue',
    'longDescription',
    'fromDate',
    'toDate',
    'sortingPriority',
  ];

  constructor(private recordService: RecordService) {}

  ngOnInit(): void {
    this.fetchAllRecords();
  }

  fetchAllRecords(): void {
    this.recordService.getAllRecords().subscribe({
      next: (data) => {
        this.records = data;
        this.isLoading = false;
        if (this.records.length === 0) {
          this.errorMessage = 'No records found.';
        }
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Failed to fetch records. Please try again later.';
        console.error(err);
      },
    });
  }
}
