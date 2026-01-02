package com.example.supermercado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.supermercado.dto.LineasVentaDTO;
import com.example.supermercado.dto.VentaDTO;
import com.example.supermercado.model.LineasVenta;
import com.example.supermercado.model.Sucursal;
import com.example.supermercado.model.Venta;
import com.example.supermercado.repository.SucursalRepository;
import com.example.supermercado.repository.VentaRepository;

public class VentaServices {

    private VentaRepository vr;
    private LineaVentaSrvice ls;
    private SucursalRepository sr;

    public VentaServices(VentaRepository vr, LineaVentaSrvice ls, SucursalRepository sr) {

        this.vr = vr;
        this.ls = ls;
        this.sr = sr;

    }

    public List<VentaDTO> listarVentas() {

        List<Venta> ventas = vr.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        for (Venta v : ventas) {

            ventasDTO.add(entityToDto(v));
        }

        return ventasDTO;
    }

    public Optional<VentaDTO> mostrarVenta(Long id){

        Optional<Venta> opt = vr.findById(id);
        if(opt.isEmpty()) return Optional.empty();
        return Optional.of(entityToDto(opt.get()));
    }

    public void nueva(VentaDTO dto){

        Venta venta = dtoToEntity(dto);
        vr.save(venta);

    }

    public VentaDTO entityToDto(Venta venta) {

        List<LineasVentaDTO> lineasDto = new ArrayList<>();

        for (LineasVenta l : venta.getLineas()) {

            lineasDto.add(ls.entityToDto(l));

        }

        return new VentaDTO(
                venta.getId(),
                venta.getSucursal().getId(),
                venta.getFecha(),
                lineasDto);
    }

    public Venta dtoToEntity(VentaDTO v) {
        List<LineasVenta> lineas = new ArrayList<>();
        if (!v.getLineas().isEmpty()){
            for (LineasVentaDTO l : v.getLineas()){
                lineas.add(ls.dtoToEntity(l));

            }
        }
        Optional<Sucursal> opt =  sr.findById(v.getIdSucursal());
        Sucursal s;
        if(opt.isEmpty()) s =  null;
        s = opt.get();

        return new Venta(
    
            v.getId(),
            s,
            v.getFecha(),
            lineas

            
    );
    }
}
