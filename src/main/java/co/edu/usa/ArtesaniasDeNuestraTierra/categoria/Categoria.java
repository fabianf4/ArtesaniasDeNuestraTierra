package co.edu.usa.ArtesaniasDeNuestraTierra.categoria;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name = "categorias")
	public class Categoria {
		
		@Id	
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		
		@Column(name = "nombre", nullable = false)
		private String nombre;
		
		private String descripcion;
		
		@Column(name = "estado", nullable = false)
		private boolean estado;
		
		@Column(name = "creado", nullable = false)
		private Date creado;
		
		@Column(name = "actualizado", nullable = false)
		private Date actualizado;

}
