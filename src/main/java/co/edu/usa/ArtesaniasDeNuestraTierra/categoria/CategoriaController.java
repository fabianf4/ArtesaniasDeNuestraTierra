package co.edu.usa.ArtesaniasDeNuestraTierra.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;	
	
	@GetMapping("/")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoriaPorId(@PathVariable("id") int id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PostMapping("/")
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable("id") int id, @RequestBody Categoria categoriaActualizada) {
        return categoriaService.actualizarCategoria(id, categoriaActualizada);
    }

    @DeleteMapping("/delete")
    public void eliminarCategoria(@PathVariable("categoria") Categoria categoria) {
        categoriaService.eliminarCategoria(categoria);
    }
