package org.roundroller.roundrollerbackend.Repository;

import org.roundroller.roundrollerbackend.Model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface participantRepository extends JpaRepository<Participant, Long> {
//    Participant findByParticipantId(long id);

    @Query(value = "SELECT p FROM Participant p WHERE p.Selected = false ORDER BY RAND() LIMIT 1")
    Participant findRandomUnselectedId();

    @Modifying
    @Query(value = "UPDATE Participant p set p.Selected = false ")
    void resetAllParticipants();

    int countBySelectedFalse();
}
