package services;

import domain.Actor;
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
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class MessageService {

    //Managed Repositories
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ActorService actorService;

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

        if(message.getId() == 0) {
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

    public Message reconstruct(Message message, BindingResult binding){
        Message result;
        if (message.getId() == 0){
            result = this.create();
        } else {
            result = this.messageRepository.findOne(message.getId());
        }

        result.setSender(message.getSender());
        result.setRecipient(message.getRecipient());
        result.setMoment(message.getMoment());
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
