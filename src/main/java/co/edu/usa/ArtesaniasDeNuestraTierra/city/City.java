package co.edu.usa.ArtesaniasDeNuestraTierra.city;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "city")
public class City
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "state_delete", nullable = false)
    private boolean stateDelete;

    @Column(name = "date_create", nullable = false)
    private Date dateCreate;

    @Column(name = "date_update", nullable = false)
    private Date dateUpdate;

}
