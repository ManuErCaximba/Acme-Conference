package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
    private Date moment;
    private String subject;
    private String body;
    private String topicEs;
    private String topicEn;


}
