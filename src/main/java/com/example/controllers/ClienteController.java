package com.example.controllers;

import com.example.models.entities.Cliente;
import com.example.services.IClienteService;
import com.example.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/listar")
    public String listAll(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Cliente> clientesPage = clienteService.findAll(pageable);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientesPage);
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("listaClientes", clientesPage);
        model.addAttribute("pageRender", pageRender);
        return "listar";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        Cliente cliente = new Cliente();

        model.put("titulo", "Formulario Cliente");
        model.put("cliente", cliente);

        return "form";
    }

    @PostMapping("/form")
    public String save(@Valid Cliente cliente, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file, RedirectAttributes flashMessage, SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario Cliente");

            return "form";
        }

        if(!file.isEmpty()){
            var rootDirectory = "C://Temp//uploads";
            try {
                byte[] bytes = file.getBytes();
                Path absoluteDirectory = Paths.get(rootDirectory + "//" + file.getOriginalFilename());
                Files.write(absoluteDirectory, bytes);
                flashMessage.addFlashAttribute("info", "Se ha subido correctamente '" + file.getOriginalFilename() + "'");

                cliente.setFoto(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String successMessage = (cliente.getId() != null) ? "Cliente editado con éxito¡" : "Cliente creado con éxito¡";

        clienteService.save(cliente);
        sessionStatus.setComplete();
        flashMessage.addFlashAttribute("success", successMessage);
        return "redirect:listar";
    }

    @GetMapping("/form/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flashMessage){
        Cliente cliente;

        if(id > 0){
            cliente = clienteService.findById(id);
            if(cliente == null){
                flashMessage.addFlashAttribute("error", "El registro no existe en la base de datos :(");
                return "redirect:/listar";
            }
        }else {
            flashMessage.addFlashAttribute("error", "El ID no puede ser cero¡");
            return "redirect:/listar";
        }

        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("cliente", cliente);

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes flashMessage){
        if(id > 0){
            clienteService.deleteById(id);
            flashMessage.addFlashAttribute("success", "Cliente eliminado con éxito¡");
            return "redirect:/listar";
        }

        return "redirect:/listar";
    }
}
