package repositories;

import domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    @Query("select c from Conference c where c.isFinal = true and CURRENT_DATE < c.startDate")
    Collection<Conference> getForthcomingConferencesFinal();

    @Query("select c from Conference c where c.isFinal = true and CURRENT_DATE > c.startDate")
    Collection<Conference> getPastConferencesFinal();

    @Query("select c from Conference c where c.isFinal = true and CURRENT_DATE > c.startDate and CURRENT_DATE < c.endDate")
    Collection<Conference> getRunningConferencesFinal();

    @Query("select c from Conference c where c.submissionDeadline between ?1 and CURRENT_DATE ")
    Collection<Conference> getConferencesSubmission5Days(Date date);

    @Query("select c from Conference c where c.notificationDeadline between CURRENT_DATE and ?1")
    Collection<Conference> getConferencesNotificationnNext4Days(Date date);

    @Query("select c from Conference c where c.cameraReadyDeadline between CURRENT_DATE and ?1")
    Collection<Conference> getConferencesCamera4Days(Date date);

    @Query("select c from Conference c where c.startDate between CURRENT_DATE and ?1")
    Collection<Conference> getConferenceStartNext4Days(Date date);

    @Query("select c from Conference c where (c.title like '%?1%' or c.venue like '%?1%' or c.summary like '%?1%') and c.isFinal = true ")
    Collection<Conference> getConferencesByKeyword(String keyword);
}
