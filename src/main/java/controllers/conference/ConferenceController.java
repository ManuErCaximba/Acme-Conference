package controllers.conference;

import controllers.AbstractController;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ConferenceService;

import java.util.Collection;

@Controller
@RequestMapping("conference")
public class ConferenceController extends AbstractController {

    @Autowired
    private ConferenceService conferenceService;

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

}
