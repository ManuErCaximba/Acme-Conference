package services;

import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.MessageRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class MessageService {

    //Managed Repositories
    @Autowired
    private MessageRepository messageRepository;

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

    public Collection<Message> findAllByActor(int actorId){
        Collection<Message> res;
        res = this.messageRepository.findAllByActor(actorId);
        Assert.notNull(res);
        return res;
    }
}
