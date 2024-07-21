package co.edu.usa.ArtesaniasDeNuestraTierra.category;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
        category.setDateCreate(new Date());
        category.setDateUpdate(new Date());
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, Category categoryUpdate) {
        return categoryRepository.findById(id)
                .map(category -> {
                	category.setName(categoryUpdate.getName());
                	category.setDescription(categoryUpdate.getDescription());
                	category.setStateDelete(true);
                	category.setDateUpdate(new Date());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category deleteCategory(Category category) {        
    	category.setStateDelete(true);
    	category.setDateUpdate(new Date());
        return categoryRepository.save(category);
    }

}
