package co.edu.usa.ArtesaniasDeNuestraTierra.region;

import java.util.Date;
import java.util.List;

import co.edu.usa.ArtesaniasDeNuestraTierra.city.City;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "region")
public class Region
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "state_delete", nullable = false)
    private boolean stateDelete;

    @Column(name = "date_create", nullable = false)
    private Date dateCreate;

    @Column(name = "date_update", nullable = false)
    private Date dateUpdate;
}