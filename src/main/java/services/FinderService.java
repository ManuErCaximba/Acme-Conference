package services;

import domain.Actor;
import domain.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.FinderRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class FinderService {

    //Managed Repositories
    @Autowired
    private FinderRepository finderRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;

    //CRUD Methods
    public Finder create(){
        Finder finder = new Finder();

        return finder;
    }

    public Finder findOne(int id){
        return this.finderRepository.findOne(id);
    }

    public Collection<Finder> findAll(){
        return this.finderRepository.findAll();
    }

    public Finder save(Finder finder){
        Finder result;
        final Actor actor = this.actorService.getActorLogged();

        Assert.notNull(actor);
        Assert.notNull(finder);

        if(finder.getId() == 0) {
            finder.setActor(actor);
        }

        result = this.finderRepository.save(finder);

        return result;
    }
}
