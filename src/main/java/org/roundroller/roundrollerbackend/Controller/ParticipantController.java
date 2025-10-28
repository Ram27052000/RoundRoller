package org.roundroller.roundrollerbackend.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.roundroller.roundrollerbackend.DTO.ParticipantRequestDTO;
import org.roundroller.roundrollerbackend.DTO.ParticipantResponseDTO;
import org.roundroller.roundrollerbackend.DTO.RollResponseDTO;
import org.roundroller.roundrollerbackend.Service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
@Tag(
        name = "Participants Management",
        description = "APIs for managing participants and rolling dice in the Round Roller app."
)
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @PostMapping("/addParticipants")
    @Operation(
            summary = "Add participants",
            description = "Adds a list of participants to the database. (Duplicates are allowed for now)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Participants added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid participant data")
    })
    public ResponseEntity<ParticipantResponseDTO> addParticipants(
            @RequestBody ParticipantRequestDTO participantRequestDTO){
        ParticipantResponseDTO participantResponseDTO = participantService
                .addParticipant(participantRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(participantResponseDTO);
    }

    @GetMapping("/getAllParticipants")
    @Operation(
            summary = "Retrieve all participants",
            description = "Fetches all participants with their names, IDs, and selection status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participants retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No participants found")
    })
    public ResponseEntity<ParticipantResponseDTO> getParticipants(){
        ParticipantResponseDTO participantResponseDTO = participantService.retrieveAllParticipants();
        return ResponseEntity.status(HttpStatus.OK).body(participantResponseDTO);
    }

    @PostMapping("/rollDice")
    @Operation(
            summary = "Roll dice to select a random participant",
            description = "Selects a random unselected participant. Resets cycle when all are selected."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participant selected successfully"),
            @ApiResponse(responseCode = "404", description = "No participants available to roll")
    })
    public ResponseEntity<RollResponseDTO> rollDice(){
        RollResponseDTO rollResponseDTO = participantService.rollDice();
        return ResponseEntity.ok(rollResponseDTO);
    }
}
