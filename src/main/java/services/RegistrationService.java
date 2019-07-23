package services;

import domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.RegistrationRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class RegistrationService {

    //Managed Repositories
    @Autowired
    private RegistrationRepository registrationRepository;


    public Collection<Registration> findAll(){
        Collection<Registration> res;
        res = this.registrationRepository.findAll();
        return res;
    }

    public Registration findOne(final int registrationId) {
        Assert.isTrue(registrationId != 0);
        final Registration res = this.registrationRepository.findOne(registrationId);
        Assert.notNull(res);
        return res;
    }
}
