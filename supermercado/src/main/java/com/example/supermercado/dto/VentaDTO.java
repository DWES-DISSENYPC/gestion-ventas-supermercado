package com.example.supermercado.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Long id;

    @NotBlank(message = "Tiene que estar asociada a una sucursal")
    private Long idSucursal;
    
    private LocalDateTime fecha;

    private List<LineasVentaDTO> lineas;


}
