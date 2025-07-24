# Generación de Colección Postman desde OpenAPI

¿Te ha pasado que llegas a un proyecto y lo primero que necesitas es probar un endpoint… pero no hay Postman, no hay Swagger, no hay nada?
 Le preguntas al último que lo trabajó y te dice: “Sí, eso está... pero tengo que buscarlo”.
Bueno, para mejorar esa situación, aquí va una guía práctica para que dejes todo documentado y exportable con un par de comandos. 😎

**En este caso hago el ejemplo con java, pero esto funciona con OpenApi, así que busca en tu lenguaje preferido como generar el openapi**


### 📋 Requisitos Previos

Tener instalado Node.js y npm.


### Agrega SpringDoc OpenAPI a tu API con WebFlux

Agrega esto a tu build.gradle o pom.xml:

implementation 'org.springdoc:springdoc-openapi-starter-webflux-ui:2.8.3'



### ⚙️ Instalar OpenAPI Generator CLI

Instala la herramienta globalmente usando npm:

npm install -g @openapitools/openapi-generator-cli



### 📥 Descargar el archivo OpenAPI (YAML)

Ejecuta el siguiente comando para obtener la especificación OpenAPI del API:

curl -o api-docs.yaml http://localhost:8080/v3/api-docs.yaml



### 📦 Generar la Colección de Postman

Usa el generador para crear la colección Postman a partir del archivo api-docs.yaml:


openapi-generator-cli generate -i api-docs.yaml -g postman-collection -o postman_collection



### 📤 Importar la colección en Postman

Sigue estos pasos para importar la colección generada en Postman:

1. Abre **Postman**.
2. Haz clic en el botón **Importar** (esquina superior izquierda).
3. Selecciona la pestaña **Archivo**.
4. Busca y selecciona el archivo `.json` dentro de la carpeta `postman_collection/` (por ejemplo, `postman_collection/collection.json`).
5. Haz clic en **Importar**.

> ✅ ¡Listo! Ahora puedes ejecutar las solicitudes definidas en tu API directamente desde Postman.



#Importante

Se debe documentar el controlador y las clases que utilices en el controlador.

Entre mas información proporciones más información te mostrará en el postman_collection.

Dile a tu asistente de AI que te ayude a documentar.

```java

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

package com.epc.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa un producto")
public class Product {

    @Schema(description = "ID del producto", defaultValue = "1", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", defaultValue = "Laptop", example = "Laptop")
    private String name;

    @Schema(description = "Precio del producto", defaultValue = "1200.0", example = "1200.0")
    private Double price;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

