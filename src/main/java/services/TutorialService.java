package services;

import domain.Actor;
import domain.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.TutorialRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Collection;

@Service
@Transactional
public class TutorialService {

    //Managed Repositories
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private Validator validator;

    public Tutorial create(){
        UserAccount userAccount;
        userAccount = LoginService.getPrincipal();

        Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

        Tutorial result;
        result = new Tutorial();

        return result;
    }

    public Tutorial save(Tutorial tutorial){
        Actor a;
        a = this.actorService.getActorLogged();

        Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(tutorial);

        Tutorial result;
        result = this.tutorialRepository.save(tutorial);

        return result;
    }

    public void delete(Tutorial tutorial){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
        Assert.notNull(tutorial);

        this.tutorialRepository.delete(tutorial);
    }

    public Collection<Tutorial> findAll(){
        Collection<Tutorial> res;
        res = this.tutorialRepository.findAll();
        return res;
    }

    public Tutorial findOne(final int tutorialId) {
        Assert.isTrue(tutorialId != 0);
        final Tutorial res = this.tutorialRepository.findOne(tutorialId);
        Assert.notNull(res);
        return res;
    }

    public Tutorial reconstruct(Tutorial tutorial, BindingResult binding){
        Tutorial result;
        if (tutorial.getId() == 0){
            result = this.create();
        } else {
            result = this.tutorialRepository.findOne(tutorial.getId());
        }

        result.setTitle(tutorial.getTitle());
        result.setStartMoment(tutorial.getStartMoment());
        result.setDuration(tutorial.getDuration());
        result.setRoom(tutorial.getRoom());
        result.setSummary(tutorial.getSummary());
        result.setAttachments(tutorial.getAttachments());

        validator.validate(result, binding);

        if (binding.hasErrors()){
            throw new ValidationException();
        }
        return result;
    }
}
