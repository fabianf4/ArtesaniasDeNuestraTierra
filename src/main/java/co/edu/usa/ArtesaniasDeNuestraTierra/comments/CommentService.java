package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.ArtesaniasDeNuestraTierra.publication.Publication;
import co.edu.usa.ArtesaniasDeNuestraTierra.publication.PublicationRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.UserRepository;

@Service
public class CommentService {
	
	@Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    public List<Comment> listComment() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("comment not found with id: " + id));
    }

    public List<Comment> getCommentsByPublicationId(int publicationId) {
        return commentRepository.findByPublicationId(publicationId);
    }

    public Comment createComment(CommentDTO commentDTO, int userId, int publicationId) {
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Publication publication = publicationRepository.findById(publicationId)
            .orElseThrow(() -> new RuntimeException("Publication not found with id: " + publicationId));
        
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setUser(user);
        comment.setPublication(publication);
        comment.setDateCreate(new Date());
        comment.setDateUpdate(new Date());
        comment.setStateDelete(false);
        
        return commentRepository.save(comment);
    }

    public Comment updateComment(int id, Comment commentUpdate, User user) {
        return commentRepository.findById(id)
                .map(comment -> {
                	comment.setText(commentUpdate.getText());                	
                	comment.setStateDelete(true);
                	comment.setUser(user); 
                	comment.setDateUpdate(new Date());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new RuntimeException("comment not found with id: " + id));
    }

    public Comment deleteComment(int id) {
    	Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    	comment.setStateDelete(true);
    	comment.setDateUpdate(new Date());
        return commentRepository.save(comment);
    }

}
