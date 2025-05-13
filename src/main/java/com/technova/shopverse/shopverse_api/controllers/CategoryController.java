package com.technova.shopverse.shopverse_api.controllers;

import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromCategoryException;
import com.technova.shopverse.shopverse_api.model.Category;
import com.technova.shopverse.shopverse_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService catServ;

    @GetMapping
    public List<Category> getAllCategories() {
        return catServ.listAllCats();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return catServ.getCatById(id);
    }

    @PostMapping
    public String createCategory(@RequestBody Category category) throws InvalidDataFromCategoryException {
        catServ.registerCat(category);
        return "Creado";
    }

    // se puede usar el <?> para indicar que el tipo puede variar
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updCat)
            throws InvalidDataFromCategoryException, CategoryNotFoundException {
        return catServ.updateCat(id,updCat);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        catServ.deleteCat(id);
    }

}
