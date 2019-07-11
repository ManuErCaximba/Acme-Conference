package controllers.author;


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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        AuthorForm authorForm;
        authorForm = new AuthorForm();
        result = this.createEditModelAndView(authorForm);

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(@Valid final AuthorForm sponsorForm, final BindingResult binding) {
        ModelAndView result;
        Author s;

        if (this.actorService.existUsername(sponsorForm.getUsername()) == false) {
            binding.rejectValue("username", "error.username");
            result = this.createEditModelAndView(sponsorForm);
        } else if (this.administratorService.checkPass(sponsorForm.getPassword(), sponsorForm.getConfirmPass()) == false) {
            binding.rejectValue("password", "error.password");
            result = this.createEditModelAndView(sponsorForm);
        } else if (binding.hasErrors())
            result = this.createEditModelAndView(sponsorForm);
        else
            try {
                s = this.authorService.reconstruct(sponsorForm, binding);
                this.authorService.save(s);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                if (binding.hasErrors())
                    result = this.createEditModelAndView(sponsorForm, "error.duplicated");
                result = this.createEditModelAndView(sponsorForm, "error.commit.error");
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

        result = new ModelAndView("author/register");
        result.addObject("authorForm", authorForm);
        result.addObject("message", messageCode);

        return result;
    }
}
