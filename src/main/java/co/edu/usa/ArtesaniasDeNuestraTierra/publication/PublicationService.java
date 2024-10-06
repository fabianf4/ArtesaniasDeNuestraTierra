package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.ArtesaniasDeNuestraTierra.category.Category;
import co.edu.usa.ArtesaniasDeNuestraTierra.category.CategoryRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.city.City;
import co.edu.usa.ArtesaniasDeNuestraTierra.city.CityRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.UserRepository;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class PublicationService {

    private PublicationRepository publicationRepository;

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    private CityRepository cityRepository;

    private final S3Client s3Client;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository,
                              CityRepository cityRepository,
                              S3Client s3Client) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
        this.s3Client = s3Client;
    }


    public List<Publication> listPublication() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(int id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found with id: " + id));
    }

    public Publication createPublication(PublicationDTO publicationDTO, int userId, int categoryId, int cityId) throws IOException {
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
    	publication.setCategories(category);
        publication.setCities(city);
        publication.setUsers(user);
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
                    publication.setUsers(user);
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

    public String subirImagenS3(byte[] imageBytes, String fileName) throws IOException {
        String bucketName = "artesanias-bucket"; // Cambia esto por el nombre de tu bucket

        // Crea la solicitud para subir el archivo
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentLength((long) imageBytes.length) // Longitud del contenido
                .contentType("image/jpeg") // Establecer el tipo de contenido
                .build();

        // Cargar el archivo en S3
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, imageBytes.length));
        }

        // Retornar la URL del archivo subido
        return s3Client.utilities().getUrl(b -> b.bucket(bucketName).key(fileName)).toExternalForm();
    }
}
