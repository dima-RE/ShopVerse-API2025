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
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository prodRepo;

    @Override
    public ResponseEntity<List<Product>> listAllProds() {
        // Optional.ofNullable() para evitar un NullPointerException.
        return Optional.of(prodRepo.findAll())
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT)
                // orElseGet() porque no se requiere un Throw y el metodo normal no sirve.
                );
    }

    @Override
    public ResponseEntity<Product> getProdById(Long id) throws ProductNotFoundException {
        return prodRepo.findById(id).map(ResponseEntity::ok).orElseThrow(
                () -> new ProductNotFoundException("Producto no encontrado con el ID: " + id));
    }

    @Override
    public Product createProd(Product dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice());
    }

    @Override
    public void saveProd(Product prod) { prodRepo.save(prod); }

    @Override
    public ResponseEntity<String> registerProd(Product prod) throws InvalidDataFromProductException {
        validateData(prod);
        saveProd(createProd(prod));
        // ¿Existiran casos de borde que puedan saltar el error de Save?
        return new ResponseEntity<>("Se creo el producto.", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateProd(Long id, Product updProd)
            throws InvalidDataFromProductException, ProductNotFoundException {
        Product prod = getProdById(id).getBody();

        if (prod != null) {
            validateData(updProd);
            prod.setName(updProd.getName());
            prod.setDescription(updProd.getDescription());
            prod.setPrice(updProd.getPrice());
            saveProd(prod);
            return new ResponseEntity<>("Se actualizo el producto.", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontró el producto.", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> deleteProd(Long id) throws ProductNotFoundException {
        Product prod = getProdById(id).getBody();

        if (prod != null) {
            prodRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void validateData(Product dto) throws InvalidDataFromProductException {
        if (dto == null) {
            throw new InvalidDataFromProductException("El producto no puede estar vacio.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDataFromProductException("El nombre no puede estar vacio.");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new InvalidDataFromProductException("La descripcion no puede estar vacia.");
        }
        if (dto.getPrice() == null || dto.getPrice() <= 0) {
            throw new InvalidDataFromProductException("El precio debe ser mayor a 0.");
        }
    }

}
