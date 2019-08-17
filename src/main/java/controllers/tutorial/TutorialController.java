package controllers.tutorial;

import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Section;
import domain.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.SectionService;
import services.TutorialService;

import java.util.Collection;

@Controller
@RequestMapping("tutorial")
public class TutorialController extends AbstractController {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        try {
            Tutorial tutorial = this.tutorialService.create();
            result = new ModelAndView("tutorial/administrator/create");
            result.addObject("tutorial", tutorial);
        } catch (Throwable oops){
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    @RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int tutorialId) {
        ModelAndView result;
        Tutorial tutorial;
        Collection<Section> sections = this.sectionService.getSectionsByTutorial(tutorialId);
        try {
            tutorial = this.tutorialService.findOne(tutorialId);
            Assert.notNull(tutorial);
            Actor user = this.actorService.getActorLogged();
            Assert.isTrue(user instanceof Administrator);
            Assert.notNull(sections);
            result = new ModelAndView("tutorial/administrator/edit");
            result.addObject("tutorial", tutorial);
            result.addObject("sections", sections);
        } catch (Throwable oops) {
            result = new ModelAndView("redirect:/");
            result.addObject("sections", sections);
        }
        return result;
    }

}
