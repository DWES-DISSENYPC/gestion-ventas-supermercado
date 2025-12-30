package com.example.supermercado.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

   
    private Long id;

    @NotEmpty(message = "El nombre es obligado")
    private String nombre;

    @NotEmpty(message = "La categoria es obligado")
    private String categoria;
    
    @NotEmpty(message = "El precio es obligado")
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

}
