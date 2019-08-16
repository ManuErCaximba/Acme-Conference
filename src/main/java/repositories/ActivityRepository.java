package repositories;

import domain.Activity;
import domain.Presentation;
import domain.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("select t from Tutorial t where t.conference.id = ?1")
    Collection<Tutorial> getTutorialsByConference(int conferenceId);

    @Query("select p from Presentation p where p.conference.id = ?1")
    Collection<Presentation> getPresentationsByConference(int conferenceId);
}
