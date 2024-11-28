import { Routes } from '@angular/router';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { SearchRecordComponent } from './components/search-record/search-record.component';
import { DeleteRecordsComponent } from './components/delete-records/delete-records.component';
import { ListRecordsComponent } from './components/list-records/list-records.component';

export const routes: Routes = [
  { path: '', redirectTo: 'upload', pathMatch: 'full' },
  { path: 'upload', component: UploadFileComponent },
  { path: 'search', component: SearchRecordComponent },
  { path: 'delete', component: DeleteRecordsComponent },
  { path: 'list', component: ListRecordsComponent },
];
