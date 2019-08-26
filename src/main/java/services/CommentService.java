package services;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.CommentRepository;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CommentService {

    //Managed Repositories
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Validator validator;


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

    public Comment create(){
        Comment res;
        res = new Comment();
        return res;
    }

    public Comment save(Comment comment){
        Date now = new Date();

        comment.setMoment(now);

        Comment result;
        result = this.commentRepository.save(comment);

        return result;
    }

    public Comment reconstruct(Comment comment, BindingResult binding){
        Comment result;
        if (comment.getId() == 0){
            result = this.create();
        } else {
            result = this.commentRepository.findOne(comment.getId());
        }

        result.setTitle(comment.getTitle());
        result.setMoment(comment.getMoment());
        result.setText(comment.getText());
        result.setActor(comment.getActor());

        validator.validate(result, binding);

        if (binding.hasErrors()){
            throw new ValidationException();
        }
        return result;
    }
}
