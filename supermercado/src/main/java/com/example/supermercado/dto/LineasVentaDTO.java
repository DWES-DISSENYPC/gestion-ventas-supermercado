package com.example.supermercado.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineasVentaDTO {

    
    private Long id;

    @NotEmpty(message = "El producto es obligado")
    private Long idProducto;

    @NotEmpty(message = "La cantidad es obligada")
    @Positive(message = "La cantidad ha de ser positiva")
    private Integer cantidad;

    @NotEmpty(message = "La venta es obligada")
    private Long idVenta;

    private String nombre;
    private Double precio;
}
