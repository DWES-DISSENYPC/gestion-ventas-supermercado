package com.example.supermercado.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.supermercado.dto.LineasVentaDTO;
import com.example.supermercado.dto.ProductoDTO;
import com.example.supermercado.dto.VentaDTO;
import com.example.supermercado.services.ProductoService;
import com.example.supermercado.services.SucursalService;
import com.example.supermercado.services.VentaServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private VentaServices vs;
    private SucursalService ss;
    private ProductoService ps;

    public VentaController(VentaServices vs, SucursalService ss, ProductoService ps) {

        this.vs = vs;
        this.ss = ss;
        this.ps = ps;

    }

    @GetMapping("/")
    public String listar(Model model) {

        List<VentaDTO> dto = vs.listarVentas();
        model.addAttribute("lista", dto);

        return "/ventas/ventas";
    }

    @GetMapping("/{id}")
    public String venta(@PathVariable Long id, Model model) {

        Optional<VentaDTO> opt = vs.mostrarVenta(id);
        if (opt.isEmpty())
            return "redirect:/ventas";
        model.addAttribute("v", opt.get());

        return "ventas/dettale";
    }

    @GetMapping("/neva")
    public String nueva(Model model) {

        VentaDTO dto = new VentaDTO();
        dto.setLineas(new ArrayList<>());
        model.addAttribute("v", dto);
        model.addAttribute("s", ss.listar());
        model.addAttribute("p", ps.listar());
        return "ventas/nueva";
    }

    @PostMapping("/nueva")
    public String guardar(@ModelAttribute("v") @Valid VentaDTO venta,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("s", ss.listar());
            model.addAttribute("p", ps.listar());
            return "ventas/nueva";
        }

        vs.nueva(venta);
        return "redirect:/ventas";
    }

    // crear linea

    @PostMapping("/nueva/linea")
    public String addLinea(@RequestParam Long idProducto,
            @RequestParam Integer cantidad,
            @ModelAttribute("v") VentaDTO venta,
            Model model) {

        // Crear la línea
        LineasVentaDTO linea = new LineasVentaDTO();
        linea.setIdProducto(idProducto);
        linea.setCantidad(cantidad);

        // Cargar datos del producto
        ProductoDTO p = ps.mostrar(idProducto).get();
        linea.setNombre(p.getNombre());
        linea.setPrecio(p.getPrecio());

        // Añadir la línea
        venta.getLineas().add(linea);

        // Volver a mostrar la vista
        model.addAttribute("v", venta);
        model.addAttribute("s", ss.listar());
        model.addAttribute("p", ps.listar());

        return "ventas/nueva";
    }

}
