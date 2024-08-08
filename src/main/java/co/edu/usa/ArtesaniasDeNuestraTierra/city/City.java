package co.edu.usa.ArtesaniasDeNuestraTierra.city;

import java.util.Date;

import co.edu.usa.ArtesaniasDeNuestraTierra.region.Region;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cities")
public class City
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
    
    @ManyToOne
    @JoinColumn(name = "regions_id")
    private Region regions;
}
