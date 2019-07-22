package controllers.administrator;

import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import forms.AdministratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdministratorService;

import javax.validation.Valid;

@Controller
@RequestMapping("administrator")
public class AdministratorController extends AbstractController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        AdministratorForm administratorForm;
        administratorForm = new AdministratorForm();
        result = this.createEditModelAndView(administratorForm);

        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid final AdministratorForm administratorForm, final BindingResult binding) {
        ModelAndView result;
        Administrator admin;

        if (this.actorService.existUsername(administratorForm.getUsername()) == false) {
            binding.rejectValue("username", "error.username");
            result = this.createEditModelAndView(administratorForm);
        } else if (!administratorForm.getPassword().equals(administratorForm.getConfirmPass())) {
            binding.rejectValue("password", "error.password");
            result = this.createEditModelAndView(administratorForm);
        } else if (binding.hasErrors())
            result = this.createEditModelAndView(administratorForm);
        else
            try {
                admin = this.administratorService.reconstruct(administratorForm, binding);
                this.administratorService.save(admin);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(administratorForm);
            }
        return result;
    }

    protected ModelAndView createEditModelAndView(final AdministratorForm adminForm) {
        ModelAndView result;
        result = this.createEditModelAndView(adminForm, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final AdministratorForm adminForm, final String messageCode) {

        final ModelAndView result;

        result = new ModelAndView("administrator/register");
        result.addObject("administratorForm", adminForm);
        result.addObject("message", messageCode);

        return result;
    }

    @RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView result;

        final Actor user = this.actorService.getActorLogged();
        final Administrator a= this.administratorService.findOne(user.getId());
        Assert.notNull(a);
        result = this.editModelAndView(a);

        return result;
    }

    @RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "update")
    public ModelAndView update(@Valid Administrator a, final BindingResult binding) {

        ModelAndView result;

        if (binding.hasErrors())
            result = this.editModelAndView(a);
        else
            try {
                a = this.administratorService.reconstruct(a, binding);
                this.administratorService.save(a);
                result = new ModelAndView("redirect:/profile/display.do");
            } catch (final Throwable oops) {
                result = this.editModelAndView(a, "actor.commit.error");
            }
        return result;
    }

    protected ModelAndView editModelAndView(final Administrator a) {
        ModelAndView result;
        result = this.editModelAndView(a, null);
        return result;
    }

    protected ModelAndView editModelAndView(final Administrator a, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("administrator/administrator/edit");
        result.addObject("administrator", a);
        result.addObject("messageCode", messageCode);

        return result;
    }

}
