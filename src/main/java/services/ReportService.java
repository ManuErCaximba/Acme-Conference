package services;

import domain.Actor;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.ReportRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ReportService {

    //Managed Repositories
    @Autowired
    private ReportRepository reportRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private ReviewerService reviewerService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private Validator validator;

    //CRUD Methods
    public Report create(int submissionId){
        Report report = new Report();
        report.setSubmission(this.submissionService.findOne(submissionId));

        return report;
    }

    public Report findOne(int id){
        return this.reportRepository.findOne(id);
    }

    public Collection<Report> findAll(){
        return this.reportRepository.findAll();
    }

    public Report save(Report report){
        Report result;
        final Actor actor = this.actorService.getActorLogged();
        final Reviewer reviewer = this.reviewerService.findOne(actor.getId());

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("REVIEWER"));
        Assert.notNull(reviewer);

        if(report.getId() == 0)
            report.setReviewer(reviewer);

        result = this.reportRepository.save(report);

        Collection<Submission> submissions = reviewer.getSubmissions();
        submissions.remove(result.getSubmission());
        reviewer.setSubmissions(submissions);

        this.reviewerService.save(reviewer);

        if(isThisSubmissionReviewered(result.getSubmission())){
            this.finalReview(result.getSubmission());
        }

        return result;
    }

    public void delete(Report report){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("REVIEWER"));
        Assert.notNull(report);
        this.reportRepository.delete(report);
    }

    //Other Methods
    public Report reconstruct(final Report report, final BindingResult binding) {
        Report result;

        result = report;
        this.validator.validate(report, binding);

        return result;
    }

    private void finalReview(Submission submission){
        Collection<Report> reports = this.getReportsOfSubmission(submission.getId());
        int accepted = 0;
        int refused = 0;
        int borderline = 0;
        for (Report r : reports){
            if (r.getDecision().equals("ACCEPT"))
                accepted++;
            else if (r.getDecision().equals("REJECT"))
                refused++;
            else
                borderline++;
        }

        if(accepted > refused)
            submission.setStatus("ACCEPTED");
        else {
            if (accepted == refused) {
                if(accepted + borderline >= refused)
                    submission.setStatus("ACCEPTED");
            } else {
                submission.setStatus("REJECTED");
            }
        }

        this.submissionService.save(submission);
    }

    public Collection<Report> getReportsMadeByReviewer(int reviewerId){
        return this.reportRepository.getReportsMadeByReviewer(reviewerId);
    }

    public boolean isThisSubmissionReviewered(Submission submission){
        return this.reportRepository.countSubmissionOnReviewers(submission) == 0;
    }

    public Collection<Report> getReportsOfSubmission(int submissionId) {
        return this.reportRepository.getReportsOfSubmission(submissionId);
    }
}
