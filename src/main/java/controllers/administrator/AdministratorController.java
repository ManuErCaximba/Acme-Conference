package controllers.administrator;

import domain.Administrator;
import forms.AdministratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdministratorService;

@Controller
@RequestMapping("administrator")
public class AdministratorController {

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

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
    public ModelAndView save(final AdministratorForm administratorForm, final BindingResult binding) {
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
                if (binding.hasErrors())
                    result = this.createEditModelAndView(administratorForm, "error.duplicated");
                result = this.createEditModelAndView(administratorForm, "error.commit.error");
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

}
