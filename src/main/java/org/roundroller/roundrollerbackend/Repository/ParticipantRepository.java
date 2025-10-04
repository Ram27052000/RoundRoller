package org.roundroller.roundrollerbackend.Repository;

import org.roundroller.roundrollerbackend.Model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
