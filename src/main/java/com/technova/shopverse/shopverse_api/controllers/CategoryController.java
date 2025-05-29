package com.technova.shopverse.shopverse_api.controllers;

import com.technova.shopverse.shopverse_api.dtos.CategoryDTO;
import com.technova.shopverse.shopverse_api.dtos.CategoryModifyDTO;
import com.technova.shopverse.shopverse_api.exceptions.CategoryNotFoundException;
import com.technova.shopverse.shopverse_api.exceptions.InvalidDataFromCategoryException;
import com.technova.shopverse.shopverse_api.services.CategoryService;
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
@RequestMapping("/api/categories")
@Tag(name = "Categorias", description = "Operaciones relacionadas a categorias.")
public class CategoryController {

    @Autowired
    private CategoryService catServ;

    @Operation(
            summary = "Obtener todas las categorias",
            description = "Este endpoint devuelve una lista con todas las categorias disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada correctamente")
    @ApiResponse(responseCode = "204", description = "Se devuelve una lista vacia, ya que no existen categorias")
    @Parameter(hidden = true)
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return catServ.listAllCatDTOs();
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return catServ.getCatById(id);
    }*/

    @Operation(
            summary = "Obtener una categoria",
            description = "Este endpoint devuelve el detalle de una categoria por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Categoria retornada correctamente")
    @ApiResponse(responseCode = "404", description = "La categoria no existe")
    @Parameter(name = "id", description = "ID de la categoria", example = "1", required = true)
    @GetMapping("/{id}/details")
    public ResponseEntity<CategoryDTO> getCategoryDetails(@PathVariable Long id) throws CategoryNotFoundException {
        return catServ.getCatDTOById(id);
    }

    @Operation(
            summary = "Crear una categoria",
            description = "Este endpoint crea una categoria"
    )
    @ApiResponse(responseCode = "201", description = "Categoria creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los parametros no son validos")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryModifyDTO category)
            throws InvalidDataFromCategoryException {
        return catServ.registerCat(category);
    }

    // se puede usar el <?> para indicar que el tipo puede variar
    @Operation(
            summary = "Modifica una categoria",
            description = "Este endpoint modifica una categoria"
    )
    @ApiResponse(responseCode = "200", description = "Categoria actualizada correctamente")
    @ApiResponse(responseCode = "400", description = "Los parametros no son validos")
    @ApiResponse(responseCode = "404", description = "La categoria a asociar no existen")
    @Parameter(name = "id", description = "ID de la categoria", example = "1", required = true)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryModifyDTO updCat)
            throws InvalidDataFromCategoryException, CategoryNotFoundException {
        return catServ.updateCat(id,updCat);
    }

    @Operation(
            summary = "Elimina una categoria",
            description = "Este endpoint elimina una categoria"
    )
    @ApiResponse(responseCode = "204", description = "Categoria eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "La categoria no existe")
    @ApiResponse(responseCode = "418", description = "La categoria tiene productos asociados")
    @Parameter(name = "id", description = "ID del producto", example = "1", required = true)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        return catServ.deleteCat(id);
    }

}
