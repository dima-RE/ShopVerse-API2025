package com.technova.shopverse.shopverse_api.services.impl;

import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromProductException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.model.Product;
import com.technova.shopverse.shopverse_api.repositories.ProductRepository;
import com.technova.shopverse.shopverse_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository prodRepo;

    @Override
    public List<Product> listAllProds() {
        return prodRepo.findAll();
    }

    @Override
    public Product getProdById(Long id) throws ProductNotFoundException {
        return prodRepo.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Producto no encontrado con el id." + id));
    }

    @Override
    public Product createProd(Product dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice());
    }

    @Override
    public void saveProd(Product prod) {
        prodRepo.save(prod);
    }

    @Override
    public void registerProd(Product prod) throws InvalidDataFromProductException {
        validateData(prod);
        saveProd(createProd(prod));
    }

    @Override
    public ResponseEntity<String> updateProd(Long id, Product updProd)
            throws InvalidDataFromProductException, ProductNotFoundException {
        Product prod = getProdById(id);

        if (prod != null) {
            validateData(updProd);
            prod.setName(updProd.getName());
            prod.setDescription(updProd.getDescription());
            prod.setPrice(updProd.getPrice());
            saveProd(prod);
            return new ResponseEntity<>("Se actualizo el producto", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteProd(Long id) throws ProductNotFoundException {
        prodRepo.deleteById(id);
    }

    public void validateData(Product dto) throws InvalidDataFromProductException {
        if (dto == null) {
            throw new InvalidDataFromProductException("El producto no puede estar vacio.");
        }
        if (dto.getName().isBlank() || dto.getName() == null) {
            throw new InvalidDataFromProductException("El nombre no puede estar vacio.");
        }
        if (dto.getDescription().isBlank() || dto.getDescription() == null) {
            throw new InvalidDataFromProductException("La descripcion no puede estar vacia.");
        }
        if (dto.getPrice() <= 0 || dto.getPrice() == null) {
            throw new InvalidDataFromProductException("El precio debe ser mayor a 0.");
        }
    }

}
