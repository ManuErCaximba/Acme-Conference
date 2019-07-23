package services;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CommentService {

    //Managed Repositories
    @Autowired
    private CommentRepository commentRepository;


    public Collection<Comment> findAll(){
        Collection<Comment> res;
        res = this.commentRepository.findAll();
        return res;
    }

    public Comment findOne(final int commentId) {
        Assert.isTrue(commentId != 0);
        final Comment res = this.commentRepository.findOne(commentId);
        Assert.notNull(res);
        return res;
    }
}
