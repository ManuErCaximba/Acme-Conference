package services;

import domain.*;
import forms.AdministratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class AdministratorService {

    //Managed Repositories
    @Autowired
    private AdministratorRepository administratorRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;
    @Autowired
    private Validator validator;

    //CRUD Methods
    public Administrator create() {

        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        final Administrator administrator = new Administrator();
        final Authority auth;
        final UserAccount userAccount;
        final Collection<Authority> authorities;
        userAccount = new UserAccount();
        auth = new Authority();
        authorities = new ArrayList<>();

        auth.setAuthority(Authority.ADMIN);
        authorities.add(auth);
        userAccount.setAuthorities(authorities);
        administrator.setUserAccount(userAccount);

        return administrator;
    }

    public Collection<Administrator> findAll() {
        Collection<Administrator> result;

        result = this.administratorRepository.findAll();

        return result;
    }

    public Administrator findOne(final int administratorId) {
        Assert.isTrue(administratorId != 0);

        Administrator result;

        result = this.administratorRepository.findOne(administratorId);

        return result;
    }

    public Administrator save(final Administrator administrator) {
        UserAccount userAccount;
        userAccount = LoginService.getPrincipal();
        Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(administrator);
        Administrator result;
        final char[] c = administrator.getPhoneNumber().toCharArray();
        /*
        if ((!administrator.getPhoneNumber().equals(null) && !administrator.getPhoneNumber().equals("")))
            if (c[0] != '+') {
                final String i = this.configurationService.findAll().get(0).getCountryCode();
                administrator.setPhoneNumber("+" + i + " " + administrator.getPhoneNumber());
            }
        */
        if (administrator.getId() == 0) {
            final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            final String res = encoder.encodePassword(administrator.getUserAccount().getPassword(), null);
            administrator.getUserAccount().setPassword(res);
        }
        result = this.administratorRepository.save(administrator);
        return result;
    }

    public void delete(final Administrator administrator) {

        final Actor actor = this.actorService.getActorLogged();
        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(administrator);
        Assert.isTrue(actor.getId() != 0);

        this.administratorRepository.delete(administrator);
    }

    /*
    public Administrator reconstruct(final Administrator admin, final BindingResult binding) {

        Administrator result;
        if (admin.getId() == 0) {
            this.validator.validate(admin, binding);
            result = admin;
        } else {
            result = this.administratorRepository.findOne(admin.getId());

            result.setName(admin.getName());
            result.setPhoto(admin.getPhoto());
            result.setPhoneNumber(admin.getPhoneNumber());
            result.setEmail(admin.getEmail());
            result.setAddress(admin.getAddress());
            result.setSurname(admin.getSurname());

            this.validator.validate(admin, binding);
        }
        return result;
    }
    */


    //Objeto formulario
    public Administrator reconstruct(final AdministratorForm admin, final BindingResult binding) {

        final Administrator result = this.create();

        result.setId(admin.getId());
        result.setVersion(admin.getVersion());

        result.setName(admin.getName());
        result.setMiddleName(admin.getMiddleName());
        result.setSurname(admin.getSurname());
        result.setEmail(admin.getEmail());
        result.setPhoto(admin.getPhoto());
        result.setPhoneNumber(admin.getPhoneNumber());
        result.setAddress(admin.getAddress());

        result.getUserAccount().setPassword(admin.getPassword());
        result.getUserAccount().setUsername(admin.getUsername());

        this.validator.validate(result, binding);
        return result;
    }

}
