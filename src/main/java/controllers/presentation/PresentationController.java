package controllers.presentation;

import controllers.AbstractController;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ConferenceService;
import services.PresentationService;

import java.util.Collection;

@Controller
@RequestMapping("presentation")
public class PresentationController extends AbstractController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ActorService actorService;


}
