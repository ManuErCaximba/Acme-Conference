package controllers.report;

import controllers.AbstractController;
import domain.Actor;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("report/reviewer")
public class ReportController extends AbstractController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private SubmissionService submissionService;

    // List --------------------------------------------------------------------------
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;

        final Reviewer reviewer = this.reviewerService.findOne(this.actorService.getActorLogged().getId());

        Collection<Report> reports = this.reportService.getReportsMadeByReviewer(reviewer.getId());

        result = new ModelAndView("report/reviewer/list");
        result.addObject("reports", reports);
        result.addObject("requestURI", "report/reviewer/list.do");

        return result;
    }

    @RequestMapping(value = "/submission/list", method = RequestMethod.GET)
    public ModelAndView submissionList() {
        ModelAndView result;

        final Reviewer reviewer = this.reviewerService.findOne(this.actorService.getActorLogged().getId());

        Collection<Submission> submissions = reviewer.getSubmissions();

        result = new ModelAndView("report/reviewer/submission/list");
        result.addObject("submissions", submissions);
        result.addObject("requestURI", "report/reviewer/submission/list.do");

        return result;
    }

    // Show --------------------------------------------------------------------------
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam final int reportId) {
        ModelAndView result;
        final Report report;

        try {
            final Reviewer reviewer = this.reviewerService.findOne(this.actorService.getActorLogged().getId());
            report = this.reportService.findOne(reportId);
            Assert.isTrue(report.getReviewer().equals(reviewer));
            result = new ModelAndView("report/reviewer/show");
            result.addObject("report", report);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    // Create ------------------------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int submissionId) {
        ModelAndView result;
        Report report;
        Actor actor = this.actorService.getActorLogged();
        Submission submission = this.submissionService.findOne(submissionId);

        try {
            Reviewer reviewer = this.reviewerService.findOne(actor.getId());
            Assert.isTrue(reviewer.getSubmissions().contains(submission));
            report = this.reportService.create(submissionId);
            report.setSubmission(submission);
            result = this.createEditModelAndView(report);
        } catch(Exception oops) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Report report, final BindingResult binding) {
        ModelAndView result;
        Reviewer reviewer = this.reviewerService.findOne(this.actorService.getActorLogged().getId());

        try {
            if (binding.hasErrors())
                result = this.createEditModelAndView(report, null, null);
            else if (!reviewer.getSubmissions().contains(report.getSubmission()))
                result = this.createEditModelAndView(report, null, null);
            else {
                report = this.reportService.reconstruct(report, binding);
                this.reportService.save(report);
                result = new ModelAndView("redirect:list.do");
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(report, "sponsorship.commit.error", null);
        }
        return result;
    }


    //ModelAndView Methods -------------------------------------------------------------
    protected ModelAndView createEditModelAndView(final Report report) {
        ModelAndView result;

        result = this.createEditModelAndView(report , null, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final Report report, final String message,
                                                  final Integer errorNumber) {
        ModelAndView result;

        String[] states = {"ACCEPT", "REJECT", "BORDER-LINE"};

        result = new ModelAndView("report/reviewer/create");

        result.addObject("report", report);
        result.addObject("states", states);
        result.addObject("message", message);
        result.addObject("errorNumber", errorNumber);

        return result;
    }
}
