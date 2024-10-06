package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import co.edu.usa.ArtesaniasDeNuestraTierra.user.UserRepository;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserService userService;

	@Autowired
	private PublicationService publicationService;
    @Autowired
    private UserRepository userRepository;

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
        try {
            Publication nuevaPublicacion = publicationService.createPublication(publicationDTO, userId, categoryId, cityId);
            System.out.println("Nueva publicación creada: " + nuevaPublicacion);

            // Verificar si la publicación se creó correctamente
            if (nuevaPublicacion == null) {
                System.out.println("Error al guardar la publicación en la base de datos");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Error al guardar en BD
            }

            // Variable para la URL de la imagen
            String urlImagen = null;

            // Verificar si se ha proporcionado un Base64 de imagen
            if (publicationDTO.getImageBase64() != null && !publicationDTO.getImageBase64().isEmpty()) {
                // Convertir la imagen Base64 a bytes
                String base64Image = publicationDTO.getImageBase64();
                String base64ImageContent = base64Image.split(",")[1]; // Extraer la parte de la imagen
                byte[] imageBytes = Base64.getDecoder().decode(base64ImageContent);

                // Subir la imagen a S3 y obtener la URL
                String originalFilename = "image_" + System.currentTimeMillis() + ".jpeg"; // Cambia la extensión según corresponda
                urlImagen = publicationService.subirImagenS3(imageBytes, originalFilename);

                // Solo actualizar la publicación si la imagen fue subida
                if (urlImagen != null) {
                    nuevaPublicacion.setImage(urlImagen);

                    // Obtener el objeto User, asegúrate de tener un método para esto
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));; // Supone que tienes un UserService

                    // Llama a updatePublication con los parámetros correctos
                    nuevaPublicacion = publicationService.updatePublication(nuevaPublicacion.getId(), nuevaPublicacion, user);
                }
            }

            return ResponseEntity.ok(nuevaPublicacion);
        } catch (IOException e) {
            // Manejo de errores en la carga de la imagen
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        } catch (Exception e) {
            // Manejo de otros errores
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
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
