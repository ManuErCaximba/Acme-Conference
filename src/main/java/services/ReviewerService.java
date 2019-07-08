package services;

import domain.Actor;
import domain.Reviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;
import repositories.ReviewerRepository;
import security.Authority;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ReviewerService {

    //Managed Repositories
    @Autowired
    private ReviewerRepository reviewerRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private Validator validator;

    //CRUD Methods
    public Collection<Reviewer> findAll() {
        Collection<Reviewer> result;

        result = this.reviewerRepository.findAll();

        return result;
    }

    public Reviewer findOne(final int reviewerId) {
        Assert.isTrue(reviewerId != 0);

        Reviewer result;

        result = this.reviewerRepository.findOne(reviewerId);

        return result;
    }

    public Reviewer create(){
        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("REVIEWER"));

        final Reviewer reviewer = new Reviewer();
        final Collection<String> keywords;
        final Authority auth;
        final UserAccount userAccount;
        final Collection<Authority> authorities;
        userAccount = new UserAccount();
        auth = new Authority();
        authorities = new ArrayList<>();
        keywords = new ArrayList<>();

        auth.setAuthority(Authority.REVIEWER);
        authorities.add(auth);
        userAccount.setAuthorities(authorities);
        reviewer.setUserAccount(userAccount);
        reviewer.setKeywords(keywords);

        return reviewer;
    }

    public Reviewer save(Reviewer reviewer){
        return this.reviewerRepository.save(reviewer);
    }

    public void delete(Reviewer reviewer){
        this.reviewerRepository.delete(reviewer);
    }
}
