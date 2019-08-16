package services;

import domain.Presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PresentationRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class PresentationService {

    //Managed Repositories
    @Autowired
    private PresentationRepository presentationRepository;

    //Supporting services

    //CRUD Methods
    public Presentation create(){
        Presentation presentation = new Presentation();

        return presentation;
    }

    public Presentation findOne(int id){
        return this.presentationRepository.findOne(id);
    }

    public Collection<Presentation> findAll(){
        return this.presentationRepository.findAll();
    }

    public Presentation save(Presentation presentation){
        Presentation result;

        Assert.notNull(presentation);

        result = this.presentationRepository.save(presentation);

        return result;
    }

    public void delete(Presentation presentation){
        Assert.notNull(presentation);

        this.presentationRepository.delete(presentation);
    }

    public Collection<Presentation> getPresentationsByConference(int conferenceId){
        Collection<Presentation> res;
        res = this.presentationRepository.getPresentationsByConference(conferenceId);
        Assert.notNull(res);
        return res;
    }
}
