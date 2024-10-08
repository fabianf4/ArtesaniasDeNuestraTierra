package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

    List<Comment> findByPublicationId(int publicationId);
}
