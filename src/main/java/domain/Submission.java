package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity {

    private String ticker;
    private Date moment;
    private String status;
    private boolean isCameraReady;

    //Relationships
    private Author author;
    private Paper paper;
    private Paper cameraReadyPaper;
    private Presentation presentation;

    @NotBlank
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @Pattern(regexp = "^ACCEPTED|REJECTED|UNDER-REVIEW$")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull
    public boolean isCameraReady() {
        return isCameraReady;
    }

    public void setCameraReady(boolean cameraReady) {
        isCameraReady = cameraReady;
    }

    //Relationships

    @ManyToOne(optional = false)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @OneToOne(optional = false)
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @OneToOne
    public Paper getCameraReadyPaper() {
        return cameraReadyPaper;
    }

    public void setCameraReadyPaper(Paper cameraReadyPaper) {
        this.cameraReadyPaper = cameraReadyPaper;
    }

    @OneToOne(optional = false)
    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }
}
