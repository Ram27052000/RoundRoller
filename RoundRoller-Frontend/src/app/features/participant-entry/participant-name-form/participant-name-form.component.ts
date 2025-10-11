import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {FormArray, FormBuilder,Validators} from '@angular/forms';
import {ParticipantApiService} from '../../../core/services/participant-api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-participant-name-form',
  imports: [],
  templateUrl: './participant-name-form.component.html',
  styleUrl: './participant-name-form.component.css'
})
export class ParticipantNameFormComponent implements OnChanges{

  @Input()
  participantCount: number = 0;
  namesFormArray: FormArray;

  constructor(private fb: FormBuilder, private participantService: ParticipantApiService, private router: Router) {
    this.namesFormArray = this.fb.array([]);
  }

  ngOnChanges(changes: SimpleChanges) {
    this.namesFormArray.clear();
    for(let i=0; i<this.participantCount; i++){
      this.namesFormArray.push(this.fb.control('',
                      [Validators.required, Validators.minLength(2)]));
    }
  }

  onSubmit(){
    const names = this.namesFormArray.value;
    const uniqueNames = new Set(names);
    if(names.length !== uniqueNames.size){
      alert("Duplicates name found")
      return;
    }
    if(this.namesFormArray.valid){
      this.participantService.addParticipants({names : names});
    }
  }
}

