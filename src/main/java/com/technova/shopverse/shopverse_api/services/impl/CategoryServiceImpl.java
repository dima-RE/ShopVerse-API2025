package com.technova.shopverse.shopverse_api.services.impl;

import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromCategoryException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.model.Category;
import com.technova.shopverse.shopverse_api.model.Product;
import com.technova.shopverse.shopverse_api.repositories.CategoryRepository;
import com.technova.shopverse.shopverse_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository catRepo;

    @Override
    public List<Category> listAllCats() {
        return catRepo.findAll();
    }

    @Override
    public Category getCatById(Long id) throws CategoryNotFoundException {
        return catRepo.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Categoria no encontrada con el id." + id));
    }

    @Override
    public Category createCat(Category dto) {
        return new Category(dto.getName(), dto.getDescription());
    }

    @Override
    public void saveCat(Category cat) {
        catRepo.save(cat);
    }

    @Override
    public void registerCat(Category cat) throws InvalidDataFromCategoryException {
        validateData(cat);
        saveCat(createCat(cat));
    }

    @Override
    public ResponseEntity<String> updateCat(Long id, Category updCat)
            throws InvalidDataFromCategoryException, CategoryNotFoundException {
        Category cat = getCatById(id);

        if (cat != null) {
            validateData(updCat);
            cat.setName(updCat.getName());
            cat.setDescription(updCat.getDescription());
            saveCat(cat);
            return new ResponseEntity<>("Se actualizo la categoria", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro la categoria", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteCat(Long id) throws CategoryNotFoundException {
        catRepo.deleteById(id);
    }

    public void validateData(Category dto) throws InvalidDataFromCategoryException {
        if (dto == null) {
            throw new InvalidDataFromCategoryException("La categoria no puede estar vacia.");
        }
        if (dto.getName().isBlank() || dto.getName() == null) {
            throw new InvalidDataFromCategoryException("El nombre no puede estar vacio.");
        }
        if (dto.getDescription().isBlank() || dto.getDescription() == null) {
            throw new InvalidDataFromCategoryException("La descripcion no puede estar vacia.");
        }
    }

}
