package com.example.supermercado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.SucursalDTO;
import com.example.supermercado.dto.VentaDTO;
import com.example.supermercado.model.Sucursal;
import com.example.supermercado.model.Venta;
import com.example.supermercado.repository.SucursalRepository;

@Service
public class SucursalService {

    private SucursalRepository sr;
    private VentaServices vs;

    public SucursalService(SucursalRepository sr, VentaServices vs) {

        this.sr = sr;
        this.vs = vs;
    }

    public List<SucursalDTO> listar() {

        List<Sucursal> sucursales = sr.findAll();
        List<SucursalDTO> sDto = new ArrayList<>();
        for (Sucursal s : sucursales) {

            sDto.add(entityToDto(s));

        }
        return sDto;
    }

    public Optional<SucursalDTO> sucursal(Long id) {

        Optional<Sucursal> opt = sr.findById(id);
        if (opt.isEmpty())
            return Optional.empty();
        return Optional.of(entityToDto(opt.get()));

    }

    public void guardar(SucursalDTO dto) {

        Sucursal s = dtoToEntity(dto);
        sr.save(s);

    }

    public void eliminar(Long id){

         sr.deleteById(id);   

    }



    private Sucursal dtoToEntity(SucursalDTO dto) {
        List<Venta> ventas = new ArrayList<>();
        if (!dto.getLista().isEmpty()) {
            
        for (VentaDTO v : dto.getLista()){

            ventas.add(vs.dtoToEntity(v));
        }

        }
        return new Sucursal(

                dto.getId(),
                dto.getNombre(),
                dto.getDireccion(),
                ventas
        );
    }

    private SucursalDTO entityToDto(Sucursal s) {
        List<VentaDTO> ventasDto = new ArrayList<>();
        for (Venta v : s.getLista()) {

            ventasDto.add(vs.entityToDto(v));
        }

        return new SucursalDTO(

                s.getId(),
                s.getNombre(),
                s.getDireccion(),
                ventasDto

        );
    }

}
