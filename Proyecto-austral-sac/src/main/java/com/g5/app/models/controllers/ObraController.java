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

import com.g5.app.models.entity.Obra;
import com.g5.app.models.service.IObraService;

@Controller
@RequestMapping(value="/obra")
@SessionAttributes("obra")
public class ObraController {
	
	@Autowired
	private IObraService obraService;
	
	@RequestMapping(value= "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Obra obra = new Obra();
		model.put("obra", obra);
		model.put("titulo", "Formulario de Obra");
		return "obra/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Obra obra,BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Obra");
			return "obra/form";
		}

		String mensajeFlash = (obra.getId() != null) ? "Obra editado con éxito!" : "Obra creado con éxito!";

		obraService.save(obra);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/obra/listar";
	}
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Obras");
	    model.addAttribute("obra", obraService.findAll());
	    return "obra/listar";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id")Long id,Map<String, Object> model,RedirectAttributes flash) {
		Obra obra =null;
		if (id > 0) {
			obra = obraService.findOne(id);
			if (obra == null) {
				flash.addFlashAttribute("error", "El ID de la obra no existe en la BBDD!");
				return "redirect:/obra/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return "redirect:/obra/listar";
		}
	    model.put("obra", obra);
		model.put("titulo", "Editar Obra");
		return "obra/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id != null) {
			obraService.delete(id);
			flash.addFlashAttribute("success", "Obra eliminado con éxito!");

		}
		return "redirect:/obra/listar";
	}
}
