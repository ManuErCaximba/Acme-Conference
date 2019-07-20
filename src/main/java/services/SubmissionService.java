package services;

import domain.Actor;
import domain.Author;
import domain.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.SubmissionRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class SubmissionService {

    //Managed Repositories
    @Autowired
    private SubmissionRepository submissionRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private AuthorService authorService;

    //CRUD Methods
    public Submission create(){
        Submission submission = new Submission();

        return submission;
    }

    public Submission findOne(int id){
        return this.submissionRepository.findOne(id);
    }

    public Collection<Submission> findAll(){
        return this.submissionRepository.findAll();
    }

    public Submission save(Submission submission){
        Submission result;
        final Actor actor = this.actorService.getActorLogged();
        final Author author = this.authorService.findOne(actor.getId());

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
        Assert.notNull(submission);

        if(submission.getId() == 0) {
            submission.setStatus("UNDER-REVIEW");
            submission.setAuthor(author);
            submission.setCameraReady(false);
            submission.setMoment(new Date());
            submission.setTicker(this.tickerGenerator(actor));
        }

        result = this.submissionRepository.save(submission);

        return result;
    }

    public void delete(Submission submission){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
        Assert.notNull(submission);

        this.submissionRepository.delete(submission);
    }

    //Other Methods
    public String tickerGenerator(Actor actor){
        String result;
        String nPart;
        String lPart;

        lPart = actor.getName().substring(0, 0).toUpperCase();

        if (actor.getMiddleName() != null && actor.getMiddleName().equals(""))
            lPart += actor.getMiddleName().substring(0, 0).toUpperCase();
        else
            lPart += "X";

        lPart += actor.getSurname().substring(0, 0).toUpperCase();

        Random random = new Random();
        int n = random.nextInt(10);
        nPart = String.valueOf(n);

        for(int i=0; i<3; i++){
            random = new Random();
            n = random.nextInt(10);
            nPart += String.valueOf(n);
        }

        result = lPart + "-" + nPart;

        return result;
    }

}
