import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from 'rxjs';
import {ParticipantResponse} from '../models/participant-response.model';
import {HttpClient} from '@angular/common/http';
import {ParticipantRequest} from '../models/participant-request.model';

@Injectable({
  providedIn: 'root'
})
export class ParticipantApiService {

  constructor(private http: HttpClient) { }

  addParticipants(participant: ParticipantRequest) : Observable<ParticipantResponse>{
      const url = '/api/participants/addParticipants'
      return this.http.post<ParticipantResponse>(url,participant).pipe(catchError(error => {
        return throwError(() => error);
      }))
  }
}
