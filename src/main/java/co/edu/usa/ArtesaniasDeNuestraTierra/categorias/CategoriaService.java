package co.edu.usa.ArtesaniasDeNuestraTierra.categorias;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
    private CategoriaRepository categoriaRepository;
	
	
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
    }

    public Categoria crearCategoria(Categoria categoria) {
        categoria.setCreado(new Date());
        categoria.setActualizado(new Date());
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(int id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());
                    categoria.setEstado(categoriaActualizada.get);
                    categoria.setActualizado(new Date());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
    }

    public Categoria eliminarCategoria(Categoria categoria) {        
        categoria.setEstado(true);
        categoria.setActualizado(new Date());
        return categoriaRepository.save(categoria);
    }
}
