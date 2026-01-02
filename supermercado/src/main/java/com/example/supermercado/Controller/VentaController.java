package com.example.supermercado.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.supermercado.dto.VentaDTO;
import com.example.supermercado.services.ProductoService;
import com.example.supermercado.services.SucursalService;
import com.example.supermercado.services.VentaServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ventas")
public class VentaController {

    private VentaServices vs;
    private SucursalService ss;
    private ProductoService ps;

    public VentaController (VentaServices vs, SucursalService ss, ProductoService ps){
    
        this.vs = vs;
        this.ss = ss;
        this.ps = ps;

    }

    @GetMapping("/")
    public String listar(Model model) {

        List<VentaDTO> dto = vs.listarVentas();
        model.addAttribute(dto);

        return "/ventas/ventas";
    }

    @GetMapping("/{id}")
    public String venta(@PathVariable Long id, Model model) {

        Optional<VentaDTO> opt = vs.mostrarVenta(id);
        if(opt.isEmpty()) return "redirect:/ventas";
        model.addAttribute("v", opt.get());

        return "ventas/dettale";
    }
    
    @GetMapping("/neva")
    public String nueva(Model model) {

        VentaDTO dto = new VentaDTO();
        model.addAttribute("v", dto);
        model.addAttribute("s", ss.listar());
        model.addAttribute("p", ps.listar());
        return "ventas/nueva";
    }
    
    @PostMapping("/nueva")
    public String nuevaPost(@ModelAttribute @Valid VentaDTO v, BindingResult result) {

        if(result.hasErrors()) return "ventas/nueva";
        vs.nueva(v);
        return "redirect:/ventas";
    }

    
    

}
