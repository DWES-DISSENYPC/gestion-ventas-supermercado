package com.example.supermercado.services;

import com.example.supermercado.dto.LineasVentaDTO;
import com.example.supermercado.model.LineasVenta;
import com.example.supermercado.model.Producto;
import com.example.supermercado.model.Venta;
import com.example.supermercado.repository.LienasVenaRepository;
import com.example.supermercado.repository.ProductoRepository;
import com.example.supermercado.repository.VentaRepository;

public class LineaVentaSrvice {

    //private LienasVenaRepository lr;
    private ProductoRepository pr;
    private VentaRepository vr;

    public LineaVentaSrvice(LienasVenaRepository lr, ProductoRepository pr, VentaRepository vr) {

        //this.lr = lr;
        this.pr = pr;
        this.vr = vr;
    }

    public LineasVentaDTO entityToDto(LineasVenta l) {

        return new LineasVentaDTO(
    
            l.getId(),
            l.getProducto().getId(),
            l.getCantidad(),
            l.getVenta().getId()

        );
    }

    public LineasVenta dtoToEntity(LineasVentaDTO linea) {

        Producto producto = pr.findById(linea.getIdProducto()).get();
        Venta venta = vr.findById(linea.getIdVenta()).get();

        return new LineasVenta(

            linea.getId(),
            producto,
            linea.getCantidad(),
            venta

        );
    }

}
