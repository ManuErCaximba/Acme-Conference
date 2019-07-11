package controllers.author;


import forms.AuthorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AuthorService;

@Controller
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        AuthorForm authorForm;
        authorForm = new AuthorForm();
        result = this.createEditModelAndView(authorForm);

        return result;
    }

    //TODO: Save

    protected ModelAndView createEditModelAndView(final AuthorForm authorForm) {
        ModelAndView result;
        result = this.createEditModelAndView(authorForm, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final AuthorForm authorForm, final String messageCode) {

        final ModelAndView result;

        result = new ModelAndView("author/register");
        result.addObject("authorForm", authorForm);
        result.addObject("message", messageCode);

        return result;
    }
}
