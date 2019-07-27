package forms;

import domain.Configuration;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import java.util.Collection;

public class ConfigurationForm {

    private int					id;
    private int					version;
    private String              systemName;
    private String              banner;
    private String              welcomeEs;
    private String              welcomeEn;
    private int                 defaultCC;
    private Collection<String>  creditCardMakes;
    private Collection<String>  topicsEs;
    private Collection<String>  topicsEn;
    private Collection<String>  voidWordsEs;
    private Collection<String>  voidWordsEn;


    public ConfigurationForm(final Configuration config) {
        this.id = config.getId();
        this.version = config.getVersion();
        this.systemName = config.getSystemName();
        this.banner = config.getBanner();
        this.welcomeEn = config.getWelcomeEn();
        this.welcomeEs = config.getWelcomeEs();
        this.defaultCC = config.getDefaultCC();
        this.creditCardMakes = config.getCreditCardMakes();
        this.topicsEn = config.getTopicsEn();
        this.topicsEs = config.getTopicsEs();
        this.voidWordsEs = config.getVoidWordsEs();
        this.voidWordsEn = config.getVoidWordsEn();
    }

    public ConfigurationForm() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getWelcomeEs() {
        return welcomeEs;
    }

    public void setWelcomeEs(String welcomeEs) {
        this.welcomeEs = welcomeEs;
    }

    public String getWelcomeEn() {
        return welcomeEn;
    }

    public void setWelcomeEn(String welcomeEn) {
        this.welcomeEn = welcomeEn;
    }

    public int getDefaultCC() {
        return defaultCC;
    }

    public void setDefaultCC(int defaultCC) {
        this.defaultCC = defaultCC;
    }

    public Collection<String> getCreditCardMakes() {
        return creditCardMakes;
    }

    public void setCreditCardMakes(Collection<String> creditCardMakes) {
        this.creditCardMakes = creditCardMakes;
    }

    public Collection<String> getTopicsEs() {
        return topicsEs;
    }

    public void setTopicsEs(Collection<String> topicsEs) {
        this.topicsEs = topicsEs;
    }

    public Collection<String> getTopicsEn() {
        return topicsEn;
    }

    public void setTopicsEn(Collection<String> topicsEn) {
        this.topicsEn = topicsEn;
    }

    public Collection<String> getVoidWordsEs() {
        return voidWordsEs;
    }

    public void setVoidWordsEs(Collection<String> voidWordsEs) {
        this.voidWordsEs = voidWordsEs;
    }

    public Collection<String> getVoidWordsEn() {
        return voidWordsEn;
    }

    public void setVoidWordsEn(Collection<String> voidWordsEn) {
        this.voidWordsEn = voidWordsEn;
    }
}
