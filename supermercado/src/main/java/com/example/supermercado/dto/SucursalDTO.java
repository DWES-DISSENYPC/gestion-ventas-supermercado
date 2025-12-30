package com.example.supermercado.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {

    private Long id;

    @NotEmpty(message = "El nombre es obligado")
    private String nombre;

    @NotEmpty(message = "La direccion es obligada")
    private String direccion;

  
    private List<VentaDTO> lista;

}
