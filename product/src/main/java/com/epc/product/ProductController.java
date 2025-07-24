package com.epc.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Operaciones CRUD sobre productos")
public class ProductController {

    @Operation(
            summary = "Obtener todos los productos",
            description = "Devuelve una lista de todos los productos disponibles.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de productos",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class))
                            )
                    )
            }
    )
    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product() {{
                    setId(1L);
                    setName("Laptop");
                    setPrice(1200.0);
                }},
                new Product() {{
                    setId(2L);
                    setName("Phone");
                    setPrice(800.0);
                }}
        );
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Devuelve un producto específico dado su ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto", example = "1", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return new Product() {{
            setId(id);
            setName("Example Product");
            setPrice(999.99);
        }};
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un producto nuevo con nombre y precio.",
            requestBody = @RequestBody(
                    description = "Datos del producto a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Nuevo producto",
                                    value = """
                                            {
                                              "name": "Tablet",
                                              "price": 499.99
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto creado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    )
            }
    )
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(123L); // Simula creación
        return product;
    }

    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza un producto existente por ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto a actualizar", example = "1")
            },
            requestBody = @RequestBody(
                    description = "Datos del producto actualizado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Producto actualizado",
                                    value = """
                                            {
                                              "name": "Updated Laptop",
                                              "price": 1100.0
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto actualizado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // Simula actualización
        return product;
    }

    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto dado su ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto a eliminar", example = "1")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto eliminado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Respuesta exitosa",
                                            value = """
                                                    {
                                                      "message": "Product with id 1 deleted successfully"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id) {
        return Map.of("message", "Product with id " + id + " deleted successfully");
    }
}
