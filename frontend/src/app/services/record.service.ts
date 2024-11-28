import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';

export interface Record {
  source: string;
  codeListCode: string;
  code: string;
  displayValue: string;
  longDescription: string;
  fromDate: string;
  toDate: string | null;
  sortingPriority: number | null;
}

@Injectable({
  providedIn: 'root'
})
export class RecordService {
  private baseUrl = 'http://localhost:8080/api/records';

  constructor(private http: HttpClient) {}

  uploadFile(file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.baseUrl}/upload`, formData, { responseType: 'text' }).pipe(
      catchError(this.handleError)
    );
  }

  getRecordByCode(code: string): Observable<Record> {
    return this.http.get<Record>(`${this.baseUrl}/${code}`).pipe(
      catchError(this.handleError)
    );
  }

  getAllRecords(): Observable<Record[]> {
    return this.http.get<Record[]>(`${this.baseUrl}`).pipe(
      catchError(this.handleError)
    );
  }

  deleteAllRecords(): Observable<string> {
    return this.http.delete(`${this.baseUrl}`, { responseType: 'text' }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      errorMessage = `Server error (Code: ${error.status}): ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));  }
}
