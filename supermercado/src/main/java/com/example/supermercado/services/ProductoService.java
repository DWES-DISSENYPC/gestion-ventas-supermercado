package com.example.supermercado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.ProductoDTO;
import com.example.supermercado.model.Producto;
import com.example.supermercado.repository.ProductoRepository;

@Service
public class ProductoService {

    private ProductoRepository pr;

    public ProductoService (ProductoRepository pr) {

        this.pr = pr;

    }

    public List<ProductoDTO> listar() {

        List<Producto> productos = pr.findAll();

        List<ProductoDTO> dtos = new ArrayList<>();
        for (Producto p : productos) {

            dtos.add(entityToDto(p));

        }
        return dtos;

    }

    public Optional<ProductoDTO> mostrar(Long id){

        Optional<Producto> opt = pr.findById(id);
        if (opt.isEmpty()) return Optional.empty();
        return Optional.of(entityToDto(opt.get()));

    }

    public void nuevo(ProductoDTO pDto) {

        Producto p = dtoToEntity(pDto);
        pr.save(p);

    }

    public void eliminar(Long id){

        pr.deleteById(id);

    }

    private Producto dtoToEntity(ProductoDTO pDto) {

        return new Producto(

            pDto.getId(),
            pDto.getNombre(),
            pDto.getCategoria(),
            pDto.getPrecio()

        );

    }

    private ProductoDTO entityToDto(Producto p) {

        return new ProductoDTO(

            p.getId(),
            p.getNombre(),
            p.getCategoria(),
            p.getPrecio()

        );

    }

}
