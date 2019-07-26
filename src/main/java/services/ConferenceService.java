package services;

import domain.Actor;
import domain.Administrator;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.ConferenceRepository;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
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

    @Autowired
    private Validator validator;

    public Conference create(){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

        Conference res = new Conference();
        return res;
    }

    public Conference saveFinal(Conference conference){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(conference);
        Conference res;
        if(conference.getId() == 0){
            Administrator admin = this.administratorService.findOne(this.actorService.getActorLogged().getId());
            conference.setAdministrator(admin);
        }
        conference.setFinal(true);
        Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()));
        Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()));
        Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()));
        Assert.isTrue(conference.getStartDate().before(conference.getEndDate()));
        res = this.conferenceRepository.save(conference);
        return res;
    }


    public Conference saveDraft(Conference conference){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(conference);
        Assert.isTrue(conference.isFinal() == false);
        Conference res;
        if(conference.getId() == 0){
            Administrator admin = this.administratorService.findOne(this.actorService.getActorLogged().getId());
            conference.setAdministrator(admin);
        }
        conference.setFinal(false);
        Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()));
        Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()));
        Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()));
        Assert.isTrue(conference.getStartDate().before(conference.getEndDate()));
        res = this.conferenceRepository.save(conference);
        return res;
    }

    public void delete (Conference conference){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

        Assert.notNull(conference);
        Assert.isTrue(conference.getId() != 0);
        Assert.isTrue(conference.isFinal() == false);

        this.conferenceRepository.delete(conference);
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

    //Reconstruct TODO: Actualizo Admin?
    public Conference reconstruct(Conference conference, BindingResult binding){
        Conference result;
        if (conference.getId() == 0){
            result = this.create();
        } else {
            result = this.conferenceRepository.findOne(conference.getId());
        }
        result.setTitle(conference.getTitle());
        result.setAcronym(conference.getAcronym());
        result.setVenue(conference.getVenue());
        result.setSubmissionDeadline(conference.getSubmissionDeadline());
        result.setNotificationDeadline(conference.getNotificationDeadline());
        result.setCameraReadyDeadline(conference.getCameraReadyDeadline());
        result.setStartDate(conference.getStartDate());
        result.setEndDate(conference.getEndDate());
        result.setSummary(conference.getSummary());
        result.setFee(conference.getFee());
        result.setFinal(conference.isFinal());
        result.setCategory(conference.getCategory());
        result.setRegistrations(conference.getRegistrations());
        result.setComments(conference.getComments());

        validator.validate(result, binding);

        if (binding.hasErrors()){
            throw new ValidationException();
        }
        return result;
    }
}
