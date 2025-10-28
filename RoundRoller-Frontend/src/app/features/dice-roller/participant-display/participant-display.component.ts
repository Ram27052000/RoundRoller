import {Component, OnInit} from '@angular/core';
import {ParticipantApiService} from '../../../core/services/participant-api.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-participant-display',
  imports: [
    NgForOf
  ],
  templateUrl: './participant-display.component.html',
  styleUrl: './participant-display.component.css'
})
export class ParticipantDisplayComponent implements OnInit{

  participantsName!: string[]
  constructor(private participateApiService: ParticipantApiService ) {
  }

  ngOnInit() {
    console.log('came here to participant')
      this.participateApiService.getParticipants().subscribe(participantsList =>{
          this.participantsName = participantsList.names;
      });
  }

}
