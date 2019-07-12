package converters;

import domain.Author;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import javax.transaction.Transactional;

@Component
@Transactional
public class AuthorToStringConverter implements Converter<Author, String> {

    @Override
    public String convert(final Author a) {
        String result;

        if (a == null)
            result = null;
        else
            result = String.valueOf(a.getId());

        return result;

    }
}
