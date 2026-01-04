package com.example.supermercado.services;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.LineasVentaDTO;
import com.example.supermercado.model.LineasVenta;
import com.example.supermercado.model.Producto;
import com.example.supermercado.model.Venta;
import com.example.supermercado.repository.LienasVenaRepository;
import com.example.supermercado.repository.ProductoRepository;
import com.example.supermercado.repository.VentaRepository;

@Service
public class LineaVentaService {

    //private LienasVenaRepository lr;
    private ProductoRepository pr;
    private VentaRepository vr;

    public LineaVentaService(LienasVenaRepository lr, ProductoRepository pr, VentaRepository vr) {

        //this.lr = lr;
        this.pr = pr;
        this.vr = vr;
    }

    public LineasVentaDTO entityToDto(LineasVenta l) {

        Producto producto = l.getProducto();
        Double precio = producto.getPrecio();
        String nombre = producto.getNombre();
        return new LineasVentaDTO(
            
            l.getId(),
            producto.getId(),
            l.getCantidad(),
            l.getVenta().getId(),
            nombre,
            precio

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
