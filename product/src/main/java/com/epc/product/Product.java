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

