// import { Component } from '@angular/core';
// import { RecordService, Record } from '../../services/record.service';
// import {JsonPipe, NgIf} from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
// import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
// import {MatButton} from '@angular/material/button';
// import {MatInput} from '@angular/material/input';
// import {MatDivider} from '@angular/material/divider';

// @Component({
//   selector: 'app-search-record',
//   standalone: true,
//   imports: [
//     FormsModule,
//     NgIf,
//     MatCard,
//     MatCardTitle,
//     MatCardContent,
//     MatFormFieldModule,
//     MatFormField,
//     MatButton,
//     MatInput,
//     MatDivider,
//     JsonPipe
//   ],
//   templateUrl: './search-record.component.html',
//   styleUrls: ['./search-record.component.scss']
// })
// export class SearchRecordComponent {
//   searchCode: string = '';
//   record: Record | null = null;
//   message: string = '';
//
//   constructor(private recordService: RecordService) {}
//
//   searchRecord(): void {
//     if (this.searchCode.trim()) {
//       this.recordService.getRecordByCode(this.searchCode.trim()).subscribe({
//         next: (response) => {
//           this.record = response;
//           this.message = '';
//         },
//         error: (err) => {
//           this.record = null;
//           this.message = `Error: ${err}`;
//         },
//       });
//     } else {
//       this.message = 'Please enter a valid code to search.';
//     }
//   }
// }
//
// //
// @Component({
//   selector: 'app-search-record',
//   templateUrl: './search-record.component.html',
//   styleUrls: ['./search-record.component.scss'],
//   standalone: true,
//   imports: [
//     FormsModule,
//     NgIf,
//     MatCard,
//     MatCardTitle,
//     MatCardContent,
//     MatFormFieldModule,
//     MatFormField,
//     MatButton,
//     MatInput,
//     MatDivider,
//     JsonPipe
//   ],
// })
// export class SearchRecordComponent {
//   searchCode: string = '';
//   record: Record | null = null;
//   message: string = '';
//
//   constructor(private recordService: RecordService) {}
//
//   searchRecord(): void {
//     this.recordService.getRecordByCode(this.searchCode).subscribe({
//       next: (response) => {
//         this.record = response;
//         this.message = '';
//       },
//       error: (err) => {
//         this.record = null;
//         this.message = `Error: ${err}`;
//       },
//     });
//   }
// }



import { Component } from '@angular/core';
import { RecordService, Record } from '../../services/record.service';
import { JsonPipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-search-record',
  standalone: true,
  imports: [
    JsonPipe,
    FormsModule,
    NgIf,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    MatFormFieldModule,
    MatButton,
    MatInput
  ],
  templateUrl: './search-record.component.html',
  styleUrls: ['./search-record.component.scss'] // Fixed 'styleUrl'
})
export class SearchRecordComponent {
  searchCode: string = '';
  record: Record | null = null;
  message: string = '';

  constructor(private recordService: RecordService) {}

  searchRecord(): void {
    if (this.searchCode.trim()) {
      this.recordService.getRecordByCode(this.searchCode.trim()).subscribe({
        next: (response) => {
          this.record = response;
          this.message = '';
        },
        error: (err) => {
          this.record = null;
          this.message = `Error: ${err}`;
        },
      });
    } else {
      this.message = 'Please enter a valid code to search.';
    }
  }
}
