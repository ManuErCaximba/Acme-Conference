package controllers.reviewer;

import controllers.AbstractController;
import domain.Reviewer;
import forms.ReviewerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ReviewerService;

import javax.validation.Valid;

@Controller
@RequestMapping("reviewer")
public class ReviewerController extends AbstractController {

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        ReviewerForm reviewerForm;
        reviewerForm = new ReviewerForm();
        result = this.createEditModelAndView(reviewerForm);

        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(@Valid final ReviewerForm reviewerForm, final BindingResult binding) {
        ModelAndView result;
        Reviewer reviewer;

        if (this.actorService.existUsername(reviewerForm.getUsername()) == false) {
            binding.rejectValue("username", "error.username");
            result = this.createEditModelAndView(reviewerForm);
        } else if (!reviewerForm.getPassword().equals(reviewerForm.getConfirmPass())) {
            binding.rejectValue("password", "error.password");
            result = this.createEditModelAndView(reviewerForm);
        } else if (binding.hasErrors())
            result = this.createEditModelAndView(reviewerForm);
        else
            try {
                reviewer = this.reviewerService.reconstruct(reviewerForm, binding);
                this.reviewerService.save(reviewer);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(reviewerForm);
            }
        return result;
    }

    protected ModelAndView createEditModelAndView(final ReviewerForm reviewerForm) {
        ModelAndView result;
        result = this.createEditModelAndView(reviewerForm, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(final ReviewerForm reviewerForm, final String messageCode) {

        final ModelAndView result;

        result = new ModelAndView("reviewer/register");
        result.addObject("reviewerForm", reviewerForm);
        result.addObject("message", messageCode);

        return result;
    }

}
