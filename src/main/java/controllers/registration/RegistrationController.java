package controllers.registration;


import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AuthorService;
import services.ConferenceService;
import services.RegistrationService;

import java.util.Collection;

@Controller
@RequestMapping("registration")
public class RegistrationController extends AbstractController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private AuthorService authorService;


    @RequestMapping(value = "/administrator/listAdmin", method = RequestMethod.GET)
    public ModelAndView listAdmin(int conferenceId){
        ModelAndView result;
        try{
            Actor actor = this.actorService.getActorLogged();
            Assert.notNull(actor);
            Assert.isTrue(actor instanceof Administrator);
            Conference conference = this.conferenceService.findOne(conferenceId);
            Assert.notNull(conference);
            Collection<Registration> registrations = conference.getRegistrations();
            Assert.notNull(registrations);
            result = new ModelAndView("registration/administrator/listAdmin");
            result.addObject("registrations", registrations);
            result.addObject("requestURI", "registration/administrator/listAdmin.do");
        }catch (Throwable oops){
            result = new ModelAndView("redirect:/");
        }
        return result;
    }


    @RequestMapping(value = "/author/listAuthor", method = RequestMethod.GET)
    public ModelAndView listAuthor(){
        ModelAndView result;
        try {
            Author author = this.authorService.findOne(this.actorService.getActorLogged().getId());
            Assert.notNull(author);
            Collection<Registration> registrations = this.registrationService.getRegistrationsPerAuthor(author.getId());
            Assert.notNull(registrations);
            result = new ModelAndView("registration/author/listAuthor");
            result.addObject("registrations", registrations);
            result.addObject("requestURI", "registration/author/listAuthor.do");

        }catch (Throwable oops){
        result = new ModelAndView("redirect:/");
        }
        return result;
    }
}
