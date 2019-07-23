package services;

import domain.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.TutorialRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class TutorialService {

    //Managed Repositories
    @Autowired
    private TutorialRepository tutorialRepository;


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
}
