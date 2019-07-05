package domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

    private Collection<String> keywords;

    @NotEmpty
    public Collection<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(Collection<String> keywords) {
        this.keywords = keywords;
    }
}
