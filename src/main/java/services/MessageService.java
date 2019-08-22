package services;

import domain.Actor;
import domain.Administrator;
import domain.Author;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageService {

    //Managed Repositories
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private Validator validator;

    public Message create() {

        final Message result = new Message();

        return result;
    }

    public Collection<Message> findAll(){
        Collection<Message> res;
        res = this.messageRepository.findAll();
        return res;
    }

    public Message findOne(final int messageId) {
        Assert.isTrue(messageId != 0);
        final Message res = this.messageRepository.findOne(messageId);
        Assert.notNull(res);
        return res;
    }

   public Message save(Message message){
        Message result;
        Assert.notNull(message);
        Date now = new Date();

        if(message.getSender() == null) {
            UserAccount userAccount = LoginService.getPrincipal();
            Actor sender = this.actorService.findByUserAccount(userAccount);
            message.setSender(sender);
        }

        message.setMoment(now);
        result = this.messageRepository.save(message);

        return result;
    }

    public Collection<Message> findAllByActor(int actorId){
        Collection<Message> res;
        res = this.messageRepository.findAllByActor(actorId);
        Assert.notNull(res);
        return res;
    }

    public void broadcast(Message message) {
        final Actor principal = this.actorService.getActorLogged();
        Assert.isTrue(principal instanceof Administrator);

        final Collection<Actor> actors = this.actorService.findAll();
        actors.remove(principal);

        for (Actor a : actors) {
            Message msg = this.create();

            msg = message;

            msg.setSender(principal);
            msg.setRecipient(a);

            msg = this.messageRepository.save(msg);

        }

    }

    public void broadcastAuthors(Message message) {
        final Actor principal = this.actorService.getActorLogged();
        Assert.isTrue(principal instanceof Administrator);

        final Collection<Author> authors = this.authorService.findAll();

        for (Author a : authors) {
            Message msg = this.create();

            msg = message;

            msg.setSender(principal);
            msg.setRecipient(a);

            msg = this.messageRepository.save(msg);

        }

    }

    public void notificationRegisterConference(Actor actor){
        Message message = this.create();

        List<Administrator> admins = new ArrayList<>(this.administratorService.findAll());
        int random = (int) (Math.random()*admins.size());

        message.setSender(admins.get(random));
        message.setRecipient(actor);
        message.setSubject("");
        message.setBody("You have successfully registered in the conference. \n Se ha registrado correctamente en la conferencia.");
    }

    public Message reconstruct(Message message, BindingResult binding){
        Message result;
        if (message.getId() == 0){
            result = this.create();
            Date now = new Date();
            result.setMoment(now);
        } else {
            result = this.messageRepository.findOne(message.getId());
        }

        result.setSender(message.getSender());
        result.setRecipient(message.getRecipient());
        result.setSubject(message.getSubject());
        result.setTopic(message.getTopic());
        result.setBody(message.getBody());
        validator.validate(result, binding);

        if (binding.hasErrors()){
            throw new ValidationException();
        }
        return result;
    }
}
