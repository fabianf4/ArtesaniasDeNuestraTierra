package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.ArtesaniasDeNuestraTierra.category.Category;
import co.edu.usa.ArtesaniasDeNuestraTierra.category.CategoryRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.city.City;
import co.edu.usa.ArtesaniasDeNuestraTierra.city.CityRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.UserRepository;

@Service
public class PublicationService {
	
	@Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CityRepository cityRepository;
	
	
    public List<Publication> listPublication() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(int id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));
    }

    public Publication createPublication(PublicationDTO publicationDTO, int userId, int categoryId, int cityId) {
        // Buscar el usuario por ID
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Buscar la categoría por ID
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Buscar la ciudad por ID
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new RuntimeException("City not found with id: " + cityId));
        
        Publication publication = new Publication();
        publication.setTitle(publicationDTO.getTitle());
        publication.setSummary(publicationDTO.getSummary());
        publication.setDescription(publicationDTO.getDescription());
        publication.setImage(publicationDTO.getImage());
    	publication.setCategory(category);
        publication.setCity(city);
        publication.setUser(user);
    	publication.setDateCreate(new Date());
    	publication.setDateUpdate(new Date());
    	publication.setStateDelete(false);
        return publicationRepository.save(publication);
    }

    public Publication updatePublication(int id, Publication publicationUpdate, User user) {
        return publicationRepository.findById(id)
                .map(publication -> {
                    publication.setTitle(publicationUpdate.getTitle());
                    publication.setSummary(publicationUpdate.getSummary());
                    publication.setDescription(publicationUpdate.getDescription());
                    publication.setImage(publicationUpdate.getImage());
                    publication.setUser(user);
                    publication.setDateUpdate(new Date());
                    return publicationRepository.save(publication);
                })
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));
    }

    public Publication deletePublication(int id) {
    	Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found"));
    	publication.setStateDelete(true);
    	publication.setDateUpdate(new Date());
        return publicationRepository.save(publication);
    }

}
