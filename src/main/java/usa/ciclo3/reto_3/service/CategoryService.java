/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Category;
import usa.ciclo3.reto_3.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }
    
    public Category save(Category category){
        if(category.getId() == null){
            return categoryRepository.save(category);
        }else { 
            Optional<Category> caux=categoryRepository.getCategory(category.getId());
            if (caux.isEmpty()) {
                return categoryRepository.save(category);
            }else{
                return category;
            }
        }  
    }
    
    public Category update(Category category){
        if(category.getId() != null){
            Optional<Category> caux=categoryRepository.getCategory(category.getId());
            if (!caux.isEmpty()) {
                if (category.getName() != null) {
                    caux.get().setName(category.getName());   
                }
                if (category.getDescription()!= null) {
                    caux.get().setDescription(category.getDescription());   
                }
                return categoryRepository.save(caux.get());
            }
        }
        return category;
    }
    
    public boolean deleteCategory(int id){
        Boolean aBoolean = getCategory(id).map(category ->{
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
