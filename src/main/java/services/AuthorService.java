
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.UserAccount;
import domain.Author;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;


	public Author create() {
		Author author;
		final Authority auth;
		final UserAccount userAccount;
		final Collection<Authority> authorities;

		author = new Author();
		userAccount = new UserAccount();
		auth = new Authority();
		authorities = new ArrayList<>();

		auth.setAuthority(Authority.AUTHOR);
		authorities.add(auth);
		userAccount.setAuthorities(authorities);
		author.setUserAccount(userAccount);

		return author;
	}

	public Collection<Author> findAll() {
		Collection<Author> res;
		res = this.authorRepository.findAll();
		return res;
	}

	public Author findOne(final int authorId) {
		Assert.isTrue(authorId != 0);
		final Author res = this.authorRepository.findOne(authorId);
		Assert.notNull(res);
		return res;
	}

	public Author save(final Author author) {
		Assert.notNull(author);
		Author result;
		if (author.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String res = encoder.encodePassword(author.getUserAccount().getPassword(), null);
			author.getUserAccount().setPassword(res);
		}
		result = this.authorRepository.save(author);
		return result;
	}

}
