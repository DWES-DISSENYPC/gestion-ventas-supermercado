package com.example.supermercado.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.supermercado.dto.SucursalDTO;
import com.example.supermercado.model.Sucursal;
import com.example.supermercado.services.SucursalService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/sucursales")
public class SucursalController {

    private SucursalService ss;

    public SucursalController( SucursalService ss) {

        this.ss = ss;

    }

    @GetMapping("/")
    public String listar(Model model) {

        List<SucursalDTO> sucursales = ss.listar();
        model.addAttribute("sucursales", sucursales);
        return "/sucursales/listar";
    }

    @GetMapping("/{id}")
    public String sucursal(@PathVariable Long id, Model model) {
        Optional<SucursalDTO> opt = ss.sucursal(id);
        if (opt.isEmpty()) return "redirect:/sucursales";
        SucursalDTO dto = opt.get();
        model.addAttribute("s", dto);
        return "/sucursales/detalle";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {

        Sucursal s = new Sucursal();
        model.addAttribute("s", s);

        return "sucursales/nueva";
    }
    
    @PostMapping("/nueva")
    public String nuevaPost(@ModelAttribute @Valid SucursalDTO s, BindingResult result) {
        
        if(result.hasErrors()) return "sucursales/nueva";
        ss.guardar(s);
        return "redirect:/sucursales";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Optional<SucursalDTO> opt = ss.sucursal(id);
        if(opt.isEmpty()) return "redirect:/sucursales";
        SucursalDTO dto = opt.get();
        model.addAttribute("s", dto);
        return "sucursales/editar";
    }

    @PostMapping("/editar/{id}")
    public String postMethodName(@ModelAttribute @Valid SucursalDTO s, BindingResult result) {
        
        if(result.hasErrors()) return "Sucursales/editar";
        ss.guardar(s);
        
        return "redirect:/sucursales/";
    }

    @GetMapping("/eliminar/{id}}")
    public String getMethodName(@PathVariable Long id) {

        ss.eliminar(id);

        return "redirect/sucursales/";
    }
    
    
    
    

}
