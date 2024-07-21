package co.edu.usa.ArtesaniasDeNuestraTierra.publication;

import java.util.Date;

import co.edu.usa.ArtesaniasDeNuestraTierra.category.Category;
import co.edu.usa.ArtesaniasDeNuestraTierra.city.City;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publications")
public class Publication {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@Column(name = "stateDelete", nullable = false)
	private boolean stateDelete;
	
	@Column(name = "date_create", nullable = false)
	private Date dateCreate;
	
	@Column(name = "date_update", nullable = false)
	private Date dateUpdate;	

}
