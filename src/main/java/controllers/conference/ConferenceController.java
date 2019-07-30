package controllers.conference;

import controllers.AbstractController;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
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

    }
}
