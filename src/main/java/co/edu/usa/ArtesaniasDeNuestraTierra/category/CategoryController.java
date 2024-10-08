package co.edu.usa.ArtesaniasDeNuestraTierra.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;	
	
	@GetMapping("/")
    public List<Category> listCategories() {
        return categoryService.listCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryPorId(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public Category UpdateCategory(@PathVariable("id") int id, @RequestBody Category categoryUpdate) {
        return categoryService.updateCategory(id, categoryUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
