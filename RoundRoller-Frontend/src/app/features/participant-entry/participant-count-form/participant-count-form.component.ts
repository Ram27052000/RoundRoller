import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatError, MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {NgIf} from '@angular/common';
@Component({
  selector: 'app-participant-count-form',
  imports: [MatFormField, MatLabel, MatInput, ReactiveFormsModule, MatButton, MatError, NgIf],
  templateUrl: './participant-count-form.component.html',
  styleUrl: './participant-count-form.component.css'
})

export class ParticipantCountFormComponent implements OnInit{

  countForm!: FormGroup;
  @Output() countOfParticipants = new EventEmitter<number>();
  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.countForm = this.fb.group({
      count: [2,[Validators.min(2), Validators.required, Validators.max(20)]]
    });
    this.countForm.get('count')?.valueChanges.subscribe(() => {
      if(this.countForm.valid){
        console.log('the value changes');
        this.countPeople();
      }
    })
  }

  countPeople() {
    if(this.countForm.valid){
      const countValue = this.countForm.get('count')?.value;
      console.log(`count value ${countValue}`);
      this.countOfParticipants.emit(countValue);
    }
  }
}
