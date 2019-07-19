package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

    private String systemName;
    private String banner;
    private String welcomeEs;
    private String welcomeEn;
    private int defaultCC;
    private Collection<String> creditCardMakes;
    private Collection<String> topicsEs;
    private Collection<String> topicsEn;
    private Collection<String> voidWords;

    @NotBlank
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @NotBlank
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @NotBlank
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getWelcomeEs() {
        return welcomeEs;
    }

    public void setWelcomeEs(String welcomeEs) {
        this.welcomeEs = welcomeEs;
    }

    @NotBlank
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public String getWelcomeEn() {
        return welcomeEn;
    }

    public void setWelcomeEn(String welcomeEn) {
        this.welcomeEn = welcomeEn;
    }

    @NotNull
    @SafeHtml(whitelistType = WhiteListType.NONE)
    public int getDefaultCC() {
        return defaultCC;
    }

    public void setDefaultCC(int defaultCC) {
        this.defaultCC = defaultCC;
    }

    @ElementCollection
    public Collection<String> getCreditCardMakes() {
        return creditCardMakes;
    }

    public void setCreditCardMakes(Collection<String> creditCardMakes) {
        this.creditCardMakes = creditCardMakes;
    }

    @ElementCollection
    public Collection<String> getTopicsEs() {
        return topicsEs;
    }

    public void setTopicsEs(Collection<String> topicsEs) {
        this.topicsEs = topicsEs;
    }

    @ElementCollection
    public Collection<String> getTopicsEn() {
        return topicsEn;
    }

    public void setTopicsEn(Collection<String> topicsEn) {
        this.topicsEn = topicsEn;
    }

    @ElementCollection
    public Collection<String> getVoidWords() {
        return voidWords;
    }

    public void setVoidWords(Collection<String> voidWords) {
        this.voidWords = voidWords;
    }
}
