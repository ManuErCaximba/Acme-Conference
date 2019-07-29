package services;

import domain.Actor;
import domain.Author;
import domain.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.PaperRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class PaperService {

    //Managed Repositories
    @Autowired
    private PaperRepository paperRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private Validator validator;

    //CRUD Methods
    public Paper create(){
        Paper paper = new Paper();

        return paper;
    }

    public Paper findOne(int id){
        return this.paperRepository.findOne(id);
    }

    public Collection<Paper> findAll(){
        return this.paperRepository.findAll();
    }

    public Paper save(Paper paper){
        Paper result;
        final Actor actor = this.actorService.getActorLogged();
        final Author author = this.authorService.findOne(actor.getId());

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
        Assert.notNull(paper);

        if(paper.getId() == 0) {
            paper.setAuthor(author);
        }

        result = this.paperRepository.save(paper);

        return result;
    }

    public void delete(Paper paper){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
        Assert.notNull(paper);

        this.paperRepository.delete(paper);
    }

    //Other methods
    public Collection<Paper> findAllByAuthor(int authorId){
        return this.paperRepository.findAllByAuthor(authorId);
    }

    public Paper reconstruct(Paper paper, BindingResult binding){
        Paper result;

        result = paper;

        if(paper.getId() != 0) {
            Author author = (Author) this.actorService.getActorLogged();
            result.setAuthor(author);
        }

        this.validator.validate(result, binding);

        return result;
    }
}
