package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;

@RestController
@RequestMapping("api/comments")
@CrossOrigin("*")
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

    @PostMapping("/{userId}/{publicationId}")
    public ResponseEntity<Comment> createComment(
        @RequestBody CommentDTO commentDTO,
        @PathVariable("userId") int userId,
        @PathVariable("publicationId") int publicationId
    ) {
        Comment createdComment = commentService.createComment(commentDTO, userId, publicationId);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody Comment comment, @RequestHeader("User-ID") int userId) {
        User user = new User();
        return ResponseEntity.ok(commentService.updateComment(id, comment, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
