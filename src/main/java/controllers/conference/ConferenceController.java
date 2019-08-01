package controllers.conference;

import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ConferenceService;

import java.util.Collection;

@Controller
@RequestMapping("conference")
public class ConferenceController extends AbstractController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ActorService actorService;


    //List forthcoming conferences

    @RequestMapping(value = "/listForthcoming", method = RequestMethod.GET)
    public ModelAndView listForthcoming(){
        ModelAndView result;
        Collection<Conference> conferences;
        conferences = this.conferenceService.getForthcomingConferencesFinal();
        final String language = LocaleContextHolder.getLocale().getLanguage();

        result = new ModelAndView("conference/listForthcoming");
        result.addObject("conferences", conferences);
        result.addObject("lang", language);
        result.addObject("requestURI", "conference/listForthcoming.do");

        return result;

    }

    //List past conferences

    @RequestMapping(value = "/listPast", method = RequestMethod.GET)
    public ModelAndView listPast(){
        ModelAndView result;
        Collection<Conference> conferences;
        conferences = this.conferenceService.getPastConferencesFinal();
        final String language = LocaleContextHolder.getLocale().getLanguage();

        result = new ModelAndView("conference/listPast");
        result.addObject("conferences", conferences);
        result.addObject("lang", language);
        result.addObject("requestURI", "conference/listPast.do");

        return result;

    }

    //List running conferences

    @RequestMapping(value = "/listRunning", method = RequestMethod.GET)
    public ModelAndView listRunning(){
        ModelAndView result;
        Collection<Conference> conferences;
        conferences = this.conferenceService.getRunningConferencesFinal();
        final String language = LocaleContextHolder.getLocale().getLanguage();

        result = new ModelAndView("conference/listRunning");
        result.addObject("conferences", conferences);
        result.addObject("lang", language);
        result.addObject("requestURI", "conference/listRunning.do");

        return result;

    }

    //TODO:Revisar
    @RequestMapping(value = "/administrator/listDeadline5Days", method = RequestMethod.GET)
    public ModelAndView listDeadline5Days(){
        ModelAndView result;
        Collection<Conference> conferences;
        conferences = this.conferenceService.getConferencesSubmission5Days();
        final String language = LocaleContextHolder.getLocale().getLanguage();

        result = new ModelAndView("conference/administrator/listDeadline5Days");
        result.addObject("conferences", conferences);
        result.addObject("lang", language);
        result.addObject("requestURI", "conference/administrator/listDeadline5Days.do");

        return result;

    }

    @RequestMapping(value = "/showNotLogged", method = RequestMethod.GET)
    public ModelAndView showNotLogged(@RequestParam int conferenceId){
        ModelAndView result;
        final Conference conference;

        try {
            conference = this.conferenceService.findOne(conferenceId);
            Assert.isTrue(conference.isFinal());
            result = new ModelAndView("conference/showNotLogged");
            result.addObject("conference", conference);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    @RequestMapping(value = "/administrator/show", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam int conferenceId){
        ModelAndView result;
        final Conference conference;

        try {
            conference = this.conferenceService.findOne(conferenceId);
            Actor actor = this.actorService.getActorLogged();
            Assert.isTrue(actor instanceof Administrator);
            Assert.isTrue(conference.isFinal());
            result = new ModelAndView("conference/administrator/show");
            result.addObject("conference", conference);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }
}
