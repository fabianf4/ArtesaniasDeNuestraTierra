package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

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
@RequestMapping("api/publications")
@CrossOrigin("*")
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;
	
    @GetMapping("/")
    public List<Publication> listCategories() {
        return publicationService.listPublication();
    }

    @GetMapping("/{id}")
    public Publication getPublicationPorId(@PathVariable("id") int id) {
        return publicationService.getPublicationById(id);
    }

    @PostMapping("/{userId}/{categoryId}/{cityId}")
    public ResponseEntity<Publication> createPublication(
        @RequestBody PublicationDTO publicationDTO,
        @PathVariable("userId") int userId,
        @PathVariable("categoryId") int categoryId,
        @PathVariable("cityId") int cityId
    ) {
        Publication createdPublication = publicationService.createPublication(publicationDTO, userId, categoryId, cityId);
        return ResponseEntity.ok(createdPublication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable int id, @RequestBody Publication publication, @RequestHeader("User-ID") int userId) {
        User user = new User();
        return ResponseEntity.ok(publicationService.updatePublication(id, publication, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable int id) {
        publicationService.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

}
