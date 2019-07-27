package controllers.configuration;

import controllers.AbstractController;
import domain.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationService;

import javax.validation.Valid;

@Controller
@RequestMapping("configuration/administrator")
public class ConfigurationController extends AbstractController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ActorService actorService;

    // Show --------------------------------------------------------------------------
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView result;
        final Configuration configuration = this.configurationService.findAll().iterator().next();

        try {
            result = new ModelAndView("configuration/administrator/show");
            result.addObject("configuration", configuration);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    // Edit ---------------------------------------------------------------------------
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView result;
        Configuration configuration = this.configurationService.findAll().iterator().next();

        try {
            result = this.createEditModelAndView(configuration);
            return result;
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
            return result;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@ModelAttribute("configurationForm") @Valid Configuration config, final BindingResult binding) {
        ModelAndView result;
        Configuration configuration;

        try {
            if (binding.hasErrors())
                result = this.createEditModelAndView(config, null);
            else {
                configuration = this.configurationService.reconstruct(config, binding);
                this.configurationService.save(configuration);
                result = new ModelAndView("redirect:show.do");
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(config, "sponsorship.commit.error");
        }
        return result;
    }

    //ModelAndView Methods -------------------------------------------------------------
    protected ModelAndView createEditModelAndView(final Configuration configuration) {
        ModelAndView result;

        result = this.createEditModelAndView(configuration, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final Configuration configuration, final String message) {
        ModelAndView result;

        result = new ModelAndView("configuration/administrator/edit");

        result.addObject("configuration", configuration);
        result.addObject("message", message);

        return result;
    }
}
