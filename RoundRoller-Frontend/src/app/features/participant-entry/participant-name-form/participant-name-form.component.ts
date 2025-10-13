import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {ParticipantApiService} from '../../../core/services/participant-api.service';
import {Router} from '@angular/router';
import {ParticipantResponse} from '../../../core/models/participant-response.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {NgForOf} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-participant-name-form',
  imports: [
    MatFormField,
    MatLabel,
    MatInput,
    NgForOf,
    ReactiveFormsModule,
    FormsModule,
    MatButton,
  ],
  templateUrl: './participant-name-form.component.html',
  styleUrl: './participant-name-form.component.css'
})
export class ParticipantNameFormComponent implements OnChanges, OnInit{

  @Input()
  participantCount: number = 0;
  namesFormArray: FormArray<FormControl>;

  constructor(private fb: FormBuilder, private participantService: ParticipantApiService, private router: Router) {
    this.namesFormArray = this.fb.array([]);
  }

  ngOnInit() {
    console.log('participant count', this.participantCount);
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
      this.participantService.addParticipants({names : names}).subscribe({
        next: (response: ParticipantResponse) => {
          console.log(`Participant Response ${response}`);
          this.router.navigate(['/dice-roller']).then(success => {
            if(success){
              console.log('Navigation passed')
            }
            else{
              console.log('navigation failed');
            }
          });
        }
      });
    }
  }
}

