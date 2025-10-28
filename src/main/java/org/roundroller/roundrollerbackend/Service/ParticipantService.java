package org.roundroller.roundrollerbackend.Service;

import org.roundroller.roundrollerbackend.DTO.ParticipantRequestDTO;
import org.roundroller.roundrollerbackend.DTO.ParticipantResponseDTO;
import org.roundroller.roundrollerbackend.DTO.RollResponseDTO;
import org.roundroller.roundrollerbackend.Exception.ParticipantNotFoundException;
import org.roundroller.roundrollerbackend.Model.Participant;
import org.roundroller.roundrollerbackend.Repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Transactional
    public ParticipantResponseDTO addParticipant
            (ParticipantRequestDTO participantRequestDTO) {
        List<Participant> participants = new ArrayList<>();
        for (String name : participantRequestDTO.getNames()) {
            Participant participant = new Participant();
            participant.setParticipantName(name);
            participant.setSelected(false);
            participants.add(participant);
        }
        List<Participant> savedParticipant = participantRepository.saveAll(participants);

        ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
        participantResponseDTO.setNames(savedParticipant
                .stream()
                .map(participant -> participant.getParticipantName()).toList());
        participantResponseDTO.setCount(savedParticipant.size());

        return participantResponseDTO;
    }

    public ParticipantResponseDTO retrieveAllParticipants() {

        ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
        List<Participant> participant = participantRepository.findAll();
        participantResponseDTO.setNames(participant.stream()
                .map(participant1 -> participant1.getParticipantName()).toList());
        participantResponseDTO.setCount(participant.size());
        participantResponseDTO.setIds(participant.stream().map(p -> p.getParticipantId()).toList());
        return participantResponseDTO;
    }

    @Transactional
    public RollResponseDTO rollDice() {
        int unselected = participantRepository.countBySelectedFalse();
        if (unselected == 0) {
            participantRepository.resetAllParticipants();
            if (participantRepository.count() == 0) {
                throw new ParticipantNotFoundException("No participants available. " +
                        "Please add participants first.");
            }
        }
        Participant participant = participantRepository.findRandomUnselectedId();
        if (participant == null) {
            throw new ParticipantNotFoundException("Failed to find participant after reset");
        }
        participant.setSelected(true);
        participantRepository.save(participant);
        int remainingCount = participantRepository.countBySelectedFalse();
        boolean cycleComplete = (remainingCount == 0);
        return new RollResponseDTO(participant.getParticipantId(),
                participant.getParticipantName(), remainingCount, cycleComplete);
    }

}
