package domain;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

    private String keyword;
    private String acronym;
    private String venue;
    private String summary;
    private Date startDate;
    private Date endDate;
    private double maximumFee;

    //Relationships
    private Category category;
    private Actor actor;
    private Collection<Conference> conferences;

    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yy HH:mm")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yy HH:mm")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @SafeHtml(whitelistType = WhiteListType.NONE)
    public double getMaximumFee() {
        return maximumFee;
    }

    public void setMaximumFee(double maximumFee) {
        this.maximumFee = maximumFee;
    }

    //Relationships

    @OneToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Valid
    @ManyToMany
    public Collection<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(Collection<Conference> conferences) {
        this.conferences = conferences;
    }
}
