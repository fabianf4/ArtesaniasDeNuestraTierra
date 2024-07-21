package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
	
	@Autowired
    private PublicationRepository publicationRepository;
	
	
    public List<Publication> listPublication() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(int id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));
    }

    public Publication createPublication(Publication publication) {
    	publication.setDateCreate(new Date());
    	publication.setDateUpdate(new Date());
        return publicationRepository.save(publication);
    }

    public Publication updatePublication(int id, Publication publicationUpdate) {
        return publicationRepository.findById(id)
                .map(publication -> {
                	publication.setTitle(publicationUpdate.getTitle());
                	publication.setSummary(publicationUpdate.getSummary());
                	publication.setDescription(publicationUpdate.getDescription());
                	publication.setImage(publicationUpdate.getImage());
                	publication.setStateDelete(true);
                	publication.setDateUpdate(new Date());
                    return publicationRepository.save(publication);
                })
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));
    }

    public Publication deletePublication(Publication publication) {        
    	publication.setStateDelete(true);
    	publication.setDateUpdate(new Date());
        return publicationRepository.save(publication);
    }

}
