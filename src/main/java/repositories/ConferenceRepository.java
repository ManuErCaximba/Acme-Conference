package repositories;

import domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    @Query("select c from Conference c where c.isFinal = true and CURRENT_DATE > c.startDate")
    Collection<Conference> getForthcomingConferencesFinal();
}
