package controllers.sponsor;

import domain.Sponsor;
import forms.SponsorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.SponsorService;

import javax.validation.Valid;

@Controller
@RequestMapping("sponsor")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        SponsorForm sponsorForm;
        sponsorForm = new SponsorForm();
        result = this.createEditModelAndView(sponsorForm);

        return result;
    }

    //TODO: Save

    protected ModelAndView createEditModelAndView(final SponsorForm sponsorForm) {
        ModelAndView result;
        result = this.createEditModelAndView(sponsorForm, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final SponsorForm sponsorForm, final String messageCode) {

        final ModelAndView result;

        result = new ModelAndView("sponsor/register");
        result.addObject("sponsorForm", sponsorForm);
        result.addObject("message", messageCode);

        return result;
    }
}
