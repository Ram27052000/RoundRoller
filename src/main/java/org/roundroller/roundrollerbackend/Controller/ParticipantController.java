package org.roundroller.roundrollerbackend.Controller;

import org.roundroller.roundrollerbackend.DTO.ParticipantRequestDTO;
import org.roundroller.roundrollerbackend.DTO.ParticipantResponseDTO;
import org.roundroller.roundrollerbackend.Service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @PostMapping("/addParticipants")
    public ResponseEntity<ParticipantResponseDTO> addParticipants
            (@RequestBody ParticipantRequestDTO participantRequestDTO){
         ParticipantResponseDTO participantResponseDTO = participantService
                 .addParticipant(participantRequestDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(participantResponseDTO);
    }
}
