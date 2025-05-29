package com.technova.shopverse.shopverse_api.controllers;

import com.technova.shopverse.shopverse_api.dtos.ProductDTO;
import com.technova.shopverse.shopverse_api.dtos.ProductModifyDTO;
import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.ProductNotFoundException;
import com.technova.shopverse.shopverse_api.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Operaciones relacionadas a productos.")
public class ProductController {
    //private final Pro
    @Autowired
    private ProductService prodServ;

    @Operation(
            summary = "Obtener todos los productos",
            description = "Este endpoint devuelve una lista con todos los productos disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente")
    @ApiResponse(responseCode = "204", description = "Se devuelve una lista vacia, ya que no existen productos")
    @Parameter(hidden = true)
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductsWithCategories() {
        return prodServ.listAllProdDTOs();
    }

    @Operation(
            summary = "Obtener todos los productos por categoria",
            description = "Este endpoint devuelve una lista con todos los productos disponibles de una categoria determinada"
    )
    @ApiResponse(responseCode = "200", description = "Lista de productos por categoria retornada correctamente")
    @ApiResponse(responseCode = "204", description = "Se devuelve una lista vacia, ya que no existen productos asociados a la categoria")
    @Parameter(name = "categoryId", description = "ID de la categoria", example = "1", required = true)
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategoryId(@PathVariable Long categoryId) {
        return prodServ.getByCategoryId(categoryId);
    }

    @Operation(
            summary = "Obtener un producto",
            description = "Este endpoint devuelve un producto por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Producto retornado correctamente")
    @ApiResponse(responseCode = "404", description = "El producto no existe")
    @Parameter(name = "id", description = "ID del producto", example = "1", required = true)
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.getProdDTOById(id);
    }

    @Operation(
            summary = "Crear un producto",
            description = "Este endpoint crea un producto"
    )
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    @ApiResponse(responseCode = "400", description = "Los parametros no son validos")
    @ApiResponse(responseCode = "404", description = "La categoria a asociar no existe")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createProd(@Valid @RequestBody ProductModifyDTO product) throws CategoryNotFoundException {
        return prodServ.registerProd(product);
    }

    @Operation(
            summary = "Modifica un producto",
            description = "Este endpoint modifica un producto"
    )
    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Los parametros no son validos")
    @ApiResponse(responseCode = "404", description = "El producto o la categoria a asociar no existen")
    @Parameter(name = "id", description = "ID del producto", example = "1", required = true)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductModifyDTO updProd)
            throws ProductNotFoundException, CategoryNotFoundException {
        return prodServ.updateProd(id,updProd);
    }

    @Operation(
            summary = "Elimina un producto",
            description = "Este endpoint elimina un producto"
    )
    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "El producto no existe")
    @Parameter(name = "id", description = "ID del producto", example = "1", required = true)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        return prodServ.deleteProd(id);
    }


}
