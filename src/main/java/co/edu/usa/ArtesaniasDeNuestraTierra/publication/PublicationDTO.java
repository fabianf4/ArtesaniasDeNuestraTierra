package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationDTO{
	private String title;
    private String summary;
    private String description;
    private String image;
}
