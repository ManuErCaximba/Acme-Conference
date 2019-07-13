

package controllers.author;

import controllers.AbstractController;
import domain.Author;
import forms.AuthorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdministratorService;
import services.AuthorService;

import javax.validation.Valid;


@Controller
@RequestMapping("author")
public class AuthorController extends AbstractController {

    @Autowired
    private AdministratorService	administratorService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ActorService			actorService;




    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        AuthorForm authorForm;
        authorForm = new AuthorForm();
        result = this.createEditModelAndView(authorForm);

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(@Valid final AuthorForm authorForm, final BindingResult binding) {
        ModelAndView result;
        Author s;

        if (this.actorService.existUsername(authorForm.getUsername()) == false) {
            binding.rejectValue("username", "error.username");
            result = this.createEditModelAndView(authorForm);
        } else if (!authorForm.getPassword().equals(authorForm.getConfirmPass())) {
            binding.rejectValue("password", "error.password");
            result = this.createEditModelAndView(authorForm);
        } else if (binding.hasErrors())
            result = this.createEditModelAndView(authorForm);
        else
            try {
                s = this.authorService.reconstruct(authorForm, binding);
                this.authorService.save(s);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                if (binding.hasErrors())
                    result = this.createEditModelAndView(authorForm, "error.duplicated");
                result = this.createEditModelAndView(authorForm, "error.commit.error");
            }
        return result;
    }

    protected ModelAndView createEditModelAndView(final AuthorForm authorForm) {
        ModelAndView result;
        result = this.createEditModelAndView(authorForm, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final AuthorForm authorForm, final String messageCode) {

        final ModelAndView result;

        result = new ModelAndView("author/create");
        result.addObject("authorForm", authorForm);
        result.addObject("message", messageCode);

        return result;
    }
}
