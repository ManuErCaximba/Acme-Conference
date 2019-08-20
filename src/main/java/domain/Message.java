package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
    private Date moment;
    private String subject;
    private String body;
    private String topicEs;
    private String topicEn;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTopicEs() {
        return topicEs;
    }

    public void setTopicEs(String topicEs) {
        this.topicEs = topicEs;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTopicEn() {
        return topicEn;
    }

    public void setTopicEn(String topicEn) {
        this.topicEn = topicEn;
    }

    // Relationships ----------------------------------------------------------
    private Actor				sender;
    private Actor	recipient;
    private Topic topic;


    @Valid
    @ManyToOne(optional = true)
    public Actor getSender() {
        return this.sender;
    }

    public void setSender(final Actor sender) {
        this.sender = sender;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Actor getRecipient() {
        return this.recipient;
    }

    public void setRecipient(final Actor recipient) {
        this.recipient = recipient;
    }

    @OneToOne(optional = false)
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
