package co.edu.usa.ArtesaniasDeNuestraTierra.comments;

import java.util.Date;

import co.edu.usa.ArtesaniasDeNuestraTierra.publication.Publication;
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
@Table(name = "comments")
public class Comment {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Publication publication;
	
	@ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "state_delete", nullable = false)
	private boolean stateDelete;
	
	@Column(name = "date_create", nullable = false)
	private Date dateCreate;
	
	@Column(name = "date_update", nullable = false)
	private Date dateUpdate;	
	

}
