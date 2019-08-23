package controllers.comment;

import controllers.AbstractController;
import domain.Comment;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CommentService;
import services.ConferenceService;

import java.util.Collection;

@Controller
@RequestMapping("comment")
public class CommentController extends AbstractController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConferenceService conferenceService;

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
}
