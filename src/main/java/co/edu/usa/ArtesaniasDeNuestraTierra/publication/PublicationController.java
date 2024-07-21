package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/publication")
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;	
	
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
	
    @GetMapping("/")
    public List<Publication> listCategories() {
        return publicationService.listPublication();
    }

    @GetMapping("/{id}")
    public Publication getPublicationPorId(@PathVariable("id") int id) {
        return publicationService.getPublicationById(id);
    }

    @PostMapping("/")
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationService.createPublication(publication);
    }

    @PutMapping("/{id}")
    public Publication UpdatePublication(@PathVariable("id") int id, @RequestBody Publication publicationUpdate) {
        return publicationService.updatePublication(id, publicationUpdate);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(@PathVariable("publication") Publication publication) {
    	publicationService.deletePublication(publication);
    }

}
