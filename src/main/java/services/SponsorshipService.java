package services;

import domain.Actor;
import domain.Sponsor;
import domain.Sponsorship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.SponsorshipRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class SponsorshipService {

    //Managed Repositories
    @Autowired
    private SponsorshipRepository sponsorshipRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private SponsorService sponsorService;

    //CRUD Methods
    public Sponsorship create(){
        Sponsorship sponsorship = new Sponsorship();

        return sponsorship;
    }

    public Sponsorship findOne(int id){
        return this.sponsorshipRepository.findOne(id);
    }

    public Collection<Sponsorship> findAll(){
        return this.sponsorshipRepository.findAll();
    }

    public Sponsorship save(Sponsorship sponsorship){
        Sponsorship result;
        final Actor actor = this.actorService.getActorLogged();
        final Sponsor sponsor = this.sponsorService.findOne(actor.getId());

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("SPONSOR"));
        Assert.notNull(sponsorship);

        if(sponsorship.getId() == 0)
            sponsorship.setSponsor(sponsor);

        result = this.sponsorshipRepository.save(sponsorship);

        return result;
    }

    public void delete(Sponsorship sponsorship){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("SPONSOR"));
        Assert.notNull(sponsorship);

        this.sponsorshipRepository.delete(sponsorship);
    }
}
