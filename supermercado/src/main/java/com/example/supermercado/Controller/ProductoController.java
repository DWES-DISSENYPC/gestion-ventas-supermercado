package com.example.supermercado.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supermercado.dto.ProductoDTO;
import com.example.supermercado.services.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/producots")
public class ProductoController {

    private ProductoService ps;

    public ProductoController(ProductoService ps) {

        this.ps = ps;

    }

    @GetMapping("/")
    public String listar(Model model) {

        List<ProductoDTO> dto = ps.listar();
        model.addAttribute("p", dto);
        return "productos/productos";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {

        Optional<ProductoDTO> opt = ps.mostrar(id);
        if(opt.isEmpty()) return "redirect:/productos";
        model.addAttribute(opt.get());
        return "/productos/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        ProductoDTO dto = new ProductoDTO();
        model.addAttribute("p", dto);

        return "productos/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevoPsot(@ModelAttribute @Valid ProductoDTO p, BindingResult result) {
        
        if(result.hasErrors()) return "/productos/nuevo";
        ps.nuevo(p);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Optional<ProductoDTO> opt = ps.mostrar(id);
        if(opt.isEmpty()) return "/productos";
        model.addAttribute("p", opt.get());

        return "productos/editar";
    }

    @PostMapping("/editar/{id}")
    public String postMethodName(@ModelAttribute @Valid ProductoDTO dto, BindingResult result) {
        if( result.hasErrors()) return "productos/ediar";
        ps.nuevo(dto);
        
        return "redirect: /productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        ps.eliminar(id);

        return "redirect: /productos";
    }
    
    

}
