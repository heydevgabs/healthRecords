import { Component } from '@angular/core';
import { RecordService } from '../../services/record.service';
import { NgIf } from '@angular/common';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-upload-file',
  standalone: true,
  imports: [
    NgIf,
    MatCard,
    MatCardContent,
    MatCardTitle,
    MatButton
  ],
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.scss']
})
export class UploadFileComponent {
  selectedFile: File | null = null;
  message: string = '';

  constructor(private recordService: RecordService) {}

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      this.selectedFile = target.files[0];
    } else {
      this.selectedFile = null;
      this.message = 'No file selected!';
    }
  }

  uploadFile(): void {
    if (this.selectedFile) {
      this.recordService.uploadFile(this.selectedFile).subscribe({
        next: (response) => (this.message = response),
        error: (err) => (this.message = `Error: ${err}`),
      });
    } else {
      this.message = 'Please select a file to upload.';
    }
  }
}
