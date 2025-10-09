import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from 'rxjs';
import {ParticipantResponse} from '../models/participant-response.model';
import {HttpClient} from '@angular/common/http';
import {ParticipantRequest} from '../models/participant-request.model';
import {RollResponse} from '../models/roll-response.model';

@Injectable({
  providedIn: 'root'
})
export class ParticipantApiService {

  constructor(private http: HttpClient) { }

  addParticipants(participant: ParticipantRequest) : Observable<ParticipantResponse>{
      const url = '/api/participants/addParticipants'
      return this.http.post<ParticipantResponse>(url,participant).pipe(catchError(error => {
        console.log(`Error while during the response ${error}`);
        return throwError(() => error);
      }))
  }

  getParticipants(): Observable<ParticipantResponse>{
      const url = '/api/participants/getAllParticipants'
      return this.http.get<ParticipantResponse>(url).pipe((catchError(error =>{
        console.log(`Error while during the response ${error}`);
        return throwError(() => error);
      })))
  }

    rollDice(): Observable<RollResponse>{
      const url = '/api/participants/rollDice'
      return this.http.post<RollResponse>(url,{}).pipe((catchError(error => {
        console.log(`Error while during the response ${error}`);
        return throwError(() => error);
      })))
    }

}
