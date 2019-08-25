package controllers.comment;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import java.util.Collection;

@Controller
@RequestMapping("comment")
public class CommentController extends AbstractController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int conferenceId){
        ModelAndView result;
        try{
            Conference conference = this.conferenceService.findOne(conferenceId);
            Assert.notNull(conference);
            Collection<Comment> comments = conference.getComments();
            result = new ModelAndView("comment/list");
            result.addObject("comments", comments);
            result.addObject("requestURI", "comment/list.do");
        }catch (Throwable oops){
            result = new ModelAndView("redirect:/");
        }
        return result;
    }

    @RequestMapping(value = "/listCommentsTutorial", method = RequestMethod.GET)
    public ModelAndView listCommentsTutorial(@RequestParam int tutorialId){
        ModelAndView result;
        try{
            Tutorial tutorial = this.tutorialService.findOne(tutorialId);
            Assert.notNull(tutorial);
            Collection<Comment> comments = tutorial.getComments();
            result = new ModelAndView("comment/listCommentsTutorial");
            result.addObject("comments", comments);
            result.addObject("requestURI", "comment/listCommentsTutorial.do");
        }catch (Throwable oops){
            result = new ModelAndView("redirect:/");
        }
        return result;
    }

    @RequestMapping(value = "/listCommentsPresentation", method = RequestMethod.GET)
    public ModelAndView listCommentsPresentation(@RequestParam int presentationId){
        ModelAndView result;
        try{
            Presentation presentation = this.presentationService.findOne(presentationId);
            Assert.notNull(presentation);
            Collection<Comment> comments = presentation.getComments();
            result = new ModelAndView("comment/listCommentsPresentation");
            result.addObject("comments", comments);
            result.addObject("requestURI", "comment/listCommentsPresentation.do");
        }catch (Throwable oops){
            result = new ModelAndView("redirect:/");
        }
        return result;
    }

    @RequestMapping(value = "/createConference", method = RequestMethod.GET)
    public ModelAndView createConference(@RequestParam int conferenceId){
        ModelAndView result;
        Conference conference;
        Actor actor = this.actorService.getActorLogged();
        try{
            conference = this.conferenceService.findOne(conferenceId);
            result = new ModelAndView("comment/createConference");
            result.addObject("conference", conference);
            result.addObject("conferenceId", conferenceId);
        } catch (Throwable oops){
            if(actor instanceof  Administrator) {
                result = new ModelAndView("redirect:conference/administrator/show.do?conferenceId="+conferenceId);
            } else {
                result = new ModelAndView("redirect:conference/showNotLogged.do?conferenceId="+conferenceId);
            }
        }
        return result;
    }

    @RequestMapping(value = "/createTutorial", method = RequestMethod.GET)
    public ModelAndView createTutorial(@RequestParam int tutorialId){
        ModelAndView result;
        Tutorial tutorial;
        try{
            tutorial = this.tutorialService.findOne(tutorialId);
            result = new ModelAndView("comment/createTutorial");
            result.addObject("tutorial", tutorial);
            result.addObject("tutorialId", tutorialId);
        } catch (Throwable oops){
            result = new ModelAndView("redirect:tutorial/show.do?tutorialId="+tutorialId);

        }
        return result;
    }

    @RequestMapping(value = "/createPresentation", method = RequestMethod.GET)
    public ModelAndView createPresentation(@RequestParam int presentationId){
        ModelAndView result;
        Presentation presentation;
        try{
            presentation = this.presentationService.findOne(presentationId);
            result = new ModelAndView("comment/createPresentation");
            result.addObject("presentation", presentation);
            result.addObject("presentationId", presentationId);
        } catch (Throwable oops){
            result = new ModelAndView("redirect:presentation/show.do?presentationId="+presentationId);

        }
        return result;
    }


}
