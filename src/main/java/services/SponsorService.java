
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;


	public Collection<Sponsor> findAll() {
		Collection<Sponsor> res;
		res = this.sponsorRepository.findAll();
		return res;
	}

	public Sponsor findOne(final int sponsorId) {
		Assert.isTrue(sponsorId != 0);
		final Sponsor res = this.sponsorRepository.findOne(sponsorId);
		Assert.notNull(res);
		return res;
	}

	public Sponsor create() {
		Sponsor sponsor;
		final Authority auth;
		final UserAccount userAccount;
		final Collection<Authority> authorities;

		sponsor = new Sponsor();
		userAccount = new UserAccount();
		auth = new Authority();
		authorities = new ArrayList<>();

		auth.setAuthority(Authority.SPONSOR);
		authorities.add(auth);
		userAccount.setAuthorities(authorities);
		sponsor.setUserAccount(userAccount);

		return sponsor;
	}

	public Sponsor save(final Sponsor sponsor) {
		Assert.notNull(sponsor);
		Sponsor result;
		if (sponsor.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String res = encoder.encodePassword(sponsor.getUserAccount().getPassword(), null);
			sponsor.getUserAccount().setPassword(res);
		}
		result = this.sponsorRepository.save(sponsor);
		return result;
	}
}
