package com.example.controllers;

import com.example.models.entities.Cliente;
import com.example.models.entities.Factura;
import com.example.models.entities.Producto;
import com.example.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/form/{id}")
    public String crear(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flashMessage){

        Cliente cliente = clienteService.findById(id);

        if (cliente == null){
            flashMessage.addFlashAttribute("error", "El cliente no existe");
            return "redirect:/listar";
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Crear factura");
        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
        return clienteService.findProductByTerm(term);
    }
}
