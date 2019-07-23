package services;

import domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.SectionRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class SectionService {

    //Managed Repositories
    @Autowired
    private SectionRepository sectionRepository;


    public Collection<Section> findAll(){
        Collection<Section> res;
        res = this.sectionRepository.findAll();
        return res;
    }

    public Section findOne(final int sectionId) {
        Assert.isTrue(sectionId != 0);
        final Section res = this.sectionRepository.findOne(sectionId);
        Assert.notNull(res);
        return res;
    }
}
