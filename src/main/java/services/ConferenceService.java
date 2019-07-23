package services;

import domain.Actor;
import domain.Administrator;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ConferenceRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ConferenceService {

    //Managed Repositories
    @Autowired
    private ConferenceRepository conferenceRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;

    @Autowired
    private AdministratorService administratorService;

    public Conference create(){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

        Conference res = new Conference();
        return res;
    }

    public Conference saveFinal(Conference conference){
        Assert.notNull(conference);
        Conference res;
        if(conference.getId() == 0){
            Administrator admin = this.administratorService.findOne(this.actorService.getActorLogged().getId());
            conference.setAdministrator(admin);
        }
        conference.setFinal(true);
        Assert.isTrue(conference.getAdministrator().getId() == this.actorService.getActorLogged().getId());
        Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()));
        Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()));
        Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()));
        Assert.isTrue(conference.getStartDate().before(conference.getEndDate()));
        res = this.conferenceRepository.save(conference);
        return res;
    }

    //TODO:Revisar
    public Conference saveDraft(Conference conference){
        Assert.notNull(conference);
        Assert.isTrue(conference.isFinal() == false);
        Conference res;
        if(conference.getId() == 0){
            Administrator admin = this.administratorService.findOne(this.actorService.getActorLogged().getId());
            conference.setAdministrator(admin);
        }
        conference.setFinal(false);
        Assert.isTrue(conference.getAdministrator().getId() == this.actorService.getActorLogged().getId());
        res = this.conferenceRepository.save(conference);
        return res;
    }

    public Collection<Conference> findAll(){
        Collection<Conference> res;
        res = this.conferenceRepository.findAll();
        return res;
    }

    public Conference findOne(final int conferenceId) {
        Assert.isTrue(conferenceId != 0);
        final Conference res = this.conferenceRepository.findOne(conferenceId);
        Assert.notNull(res);
        return res;
    }
}
