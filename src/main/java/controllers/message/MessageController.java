package controllers.message;

import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import java.util.Collection;

@Controller
@RequestMapping("message")
public class MessageController extends AbstractController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/administrator,author,reviewer,sponsor/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;
        Collection<Message> messages;
        Collection<Message> messagesReceived;
        String language = LocaleContextHolder.getLocale().getLanguage();
        try {
            Actor actor = this.actorService.getActorLogged();
            Assert.notNull(actor);

            messages = this.messageService.findAllBySender(actor.getId());
            Assert.notNull(messages);

            messagesReceived = this.messageService.findAllByRecipient(actor.getId());
            Assert.notNull(messagesReceived);

            messages.addAll(messagesReceived);
            Assert.notNull(messages);

            result = new ModelAndView("message/administrator,author,reviewer,sponsor/list");
            result.addObject("messages", messages);
            result.addObject("lang", language);
            result.addObject("requestURI", "message/administrator,author,reviewer,sponsor/list.do");
        } catch (Exception e){
            result = new ModelAndView("redirect:/");
        }
        return result;

    }


    @RequestMapping(value = "/administrator,author,reviewer,sponsor/show", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam int messageId){
        ModelAndView result;
        Message message;
        String language = LocaleContextHolder.getLocale().getLanguage();
        try {
            message = this.messageService.findOne(messageId);
            Assert.notNull(message);

            Actor actor = this.actorService.getActorLogged();
            Assert.isTrue(message.getSender().getId() == actor.getId() || message.getRecipients().iterator().next().getId() == actor.getId());
            result = new ModelAndView("message/administrator,author,reviewer,sponsor/show");
            result.addObject("message", message);
            result.addObject("lang", language);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }
}
