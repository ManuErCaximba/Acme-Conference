package services;

import domain.Actor;
import domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.TopicRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Collection;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private Validator validator;

    public Topic create(){
        UserAccount userAccount;
        userAccount = LoginService.getPrincipal();

        Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

        Topic result;
        result = new Topic();

        return result;
    }

    public Collection<Topic> findAll(){
        Collection<Topic> res;
        res = this.topicRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Topic findOne(int topicId){
        Topic res;
        res = this.topicRepository.findOne(topicId);
        Assert.notNull(res);
        return res;
    }

    public Topic save(Topic topic){
        Actor a;
        a = this.actorService.getActorLogged();

        Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(topic);

        Topic result;
        result = this.topicRepository.save(topic);

        return result;
    }

    public void delete(Topic topic){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(topic);

        this.topicRepository.delete(topic);
    }

    public Topic reconstruct(Topic topic, BindingResult binding){
        Topic result;
        if (topic.getId() == 0){
            result = this.create();
        } else {
            result = this.topicRepository.findOne(topic.getId());
        }

        result.setNameEn(topic.getNameEn());
        result.setNameEs(topic.getNameEs());

        validator.validate(result, binding);

        if (binding.hasErrors()){
            throw new ValidationException();
        }
        return result;
    }
}
