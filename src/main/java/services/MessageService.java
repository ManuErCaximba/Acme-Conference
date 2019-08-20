package services;

import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.MessageRepository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Transactional
public class MessageService {

    //Managed Repositories
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ActorService actorService;

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

   /* public Message save(Message message){
        Assert.notNull(message);
        Date now = new Date();

    }
    */

    public Collection<Message> findAllBySender(int senderId){
        Collection<Message> res;
        res = this.messageRepository.findAllBySender(senderId);
        Assert.notNull(res);
        return res;
    }

    public Collection<Message> findAllByRecipient(int actorId){
        Actor actor = this.actorService.findOne(actorId);
        Assert.notNull(actor);

        Collection<Message> res;
        res = this.messageRepository.findAllByRecipient(actor);
        Assert.notNull(res);

        return res;
    }
}
