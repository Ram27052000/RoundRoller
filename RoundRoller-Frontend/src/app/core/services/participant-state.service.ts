import { Injectable } from '@angular/core';
import {ParticipantApiService} from './participant-api.service';

@Injectable({
  providedIn: 'root'
})
export class ParticipantStateService {

  constructor(private participantApiService: ParticipantApiService) { }

  // loadParticipants(){
  //   this.participantApiService.getParticipants().subscribe(data =>{
  //     const participantResponse= data.ids.map((id, index) =>({
  //
  //     }))
  //   })
  // }
}
