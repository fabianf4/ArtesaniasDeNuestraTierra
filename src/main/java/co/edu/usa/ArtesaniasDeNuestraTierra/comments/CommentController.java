package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CommentController {
	
	@Autowired
	private CommentService commentService;	
	
	@GetMapping("/")
    public List<Comment> listCategories() {
        return commentService.listComment();
    }

    @GetMapping("/{id}")
    public Comment getCommentPorId(@PathVariable("id") int id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public Comment UpdateComment(@PathVariable("id") int id, @RequestBody Comment commentUpdate) {
        return commentService.updateComment(id, commentUpdate);
    }

    @DeleteMapping("/delete")
    public void deleteComment(@PathVariable("comment") Comment comment) {
    	commentService.deleteComment(comment);
    }

}
