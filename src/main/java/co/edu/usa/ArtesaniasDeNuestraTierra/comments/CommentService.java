package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CommentService {
	
	@Autowired
    private CommentRepository commentRepository;
	
	
    public List<Comment> listComment() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("comment not found with id: " + id));
    }

    public Comment createComment(Comment comment) {
    	comment.setDateCreate(new Date());
    	comment.setDateUpdate(new Date());
        return commentRepository.save(comment);
    }

    public Comment updateComment(int id, Comment commentUpdate) {
        return commentRepository.findById(id)
                .map(comment -> {
                	comment.setText(commentUpdate.getText());                	
                	comment.setStateDelete(true);
                	comment.setDateUpdate(new Date());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new RuntimeException("comment not found with id: " + id));
    }

    public Comment deleteComment(Comment comment) {        
    	comment.setStateDelete(true);
    	comment.setDateUpdate(new Date());
        return commentRepository.save(comment);
    }

}
