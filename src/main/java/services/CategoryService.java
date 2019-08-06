package services;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Collection<Category> findAll() {
        Collection<Category> res;
        res = this.categoryRepository.findAll();
        Assert.notNull(res);
        return res;
    }
}
