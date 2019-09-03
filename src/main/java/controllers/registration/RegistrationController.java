package controllers.registration;


import controllers.AbstractController;
import domain.*;
import forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Calendar;
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

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MessageService messageService;
    

    @RequestMapping(value = "/administrator/listAdmin", method = RequestMethod.GET)
    public ModelAndView listAdmin(@RequestParam int conferenceId){
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

            Author author = this.authorService.findOne(this.actorService.getActorLogged().getId());
            Assert.notNull(author);
            Collection<Conference> conferences = this.conferenceService.getConferencesByAuthor(author.getId());
            Assert.notNull(conferences);
            result = new ModelAndView("registration/author/listAuthor");
            result.addObject("conferences", conferences);
            result.addObject("author", author);
            result.addObject("requestURI", "registration/author/listAuthor.do");


        return result;
    }

    @RequestMapping(value = "/author/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int conferenceId){
        ModelAndView result;
        Registration registration;

        registration = this.registrationService.create();
        result = this.createEditModelAndView(registration, conferenceId);

        return result;
    }

    @RequestMapping(value = "/author/create", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm, int conferenceId, BindingResult binding){
        ModelAndView result;
        Registration registration;
        Calendar now = Calendar.getInstance();
        Collection<String> brandList = this.configurationService.findAll().iterator().next().getCreditCardMakes();
        Author author = this.authorService.findOne(this.actorService.getActorLogged().getId());
        try {
            Assert.notNull(brandList);
            if (binding.hasErrors())
                result = this.createEditModelAndView(registrationForm, conferenceId, null, null);
            else if (registrationForm.getExpirationYear() < now.get(Calendar.YEAR))
                result = this.createEditModelAndView(registrationForm, conferenceId, null, 1);
            else if (registrationForm.getExpirationYear() == now.get(Calendar.YEAR) &&
                    registrationForm.getExpirationMonth() < now.get(Calendar.MONTH))
                result = this.createEditModelAndView(registrationForm, conferenceId,  null, 2);
            else if (!brandList.contains(registrationForm.getBrandName()))
                result = this.createEditModelAndView(registrationForm, conferenceId, null, null);
            else {
                registration = this.registrationService.reconstruct(registrationForm, binding);
                this.registrationService.save(registration, conferenceId);
                this.messageService.notificationRegisterConference(author, conferenceId);
                result = new ModelAndView("redirect:listAuthor.do");
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(registrationForm, conferenceId,"registration.commit.error", null);
        }
        return result;
    }

    protected ModelAndView createEditModelAndView(final Registration registration, int conferenceId) {
        ModelAndView result;

        result = this.createEditModelAndView(new RegistrationForm(), conferenceId, null, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final RegistrationForm registrationForm, int conferenceId, final String message,
                                                  final Integer errorNumber) {
        ModelAndView result;
        Collection<String> brandList = this.configurationService.findAll().iterator().next().getCreditCardMakes();

        result = new ModelAndView("registration/author/create");
        result.addObject("registrationForm", registrationForm);
        result.addObject("message", message);
        result.addObject("errorNumber", errorNumber);
        result.addObject("brandList", brandList);
        result.addObject("conferenceId", conferenceId);

        return result;
    }
}
