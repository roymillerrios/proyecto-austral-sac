package com.g5.app.models.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g5.app.models.entity.Inventario;
import com.g5.app.models.service.IInventarioService;

@Controller
@RequestMapping(value="/inventario")
@SessionAttributes("inventario")
public class InventarioController {
	
	@Autowired
	private IInventarioService inventarioService;
	
	@RequestMapping(value= "/inicio", method = RequestMethod.GET)
	public String inicio(Model model) {
		return "inventario/inicio";
	}
	
	@RequestMapping(value= "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Inventario inventario = new Inventario();
		model.put("inventario", inventario);
		model.put("titulo", "Formulario de Inventario");
		return "inventario/form";
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Inventario inventario,BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Inventario");
			return "inventario/form";
		}

		String mensajeFlash = (inventario.getId() != null) ? "Inventario editado con éxito!" : "Inventario creado con éxito!";

		inventarioService.save(inventario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/inventario/listar";
	}
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Inventarios");
	    model.addAttribute("inventario", inventarioService.findAll());
	    return "inventario/listar";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id")Long id,Map<String, Object> model,RedirectAttributes flash) {
		Inventario inventario =null;
		if (id > 0) {
			inventario = inventarioService.findOne(id);
			if (inventario == null) {
				flash.addFlashAttribute("error", "El ID del tipo no existe en la BBDD!");
				return "redirect:/inventario/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del tipo no puede ser cero!");
			return "redirect:/inventario/listar";
		}
	    model.put("inventario", inventario);
		model.put("titulo", "Editar Inventario");
		return "inventario/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id != null) {
			inventarioService.delete(id);
			flash.addFlashAttribute("success", "Inventario eliminado con éxito!");

		}
		return "redirect:/inventario/listar";
	}
}
