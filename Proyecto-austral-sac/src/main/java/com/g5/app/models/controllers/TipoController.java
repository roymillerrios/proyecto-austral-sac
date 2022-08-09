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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g5.app.models.entity.Tipo;
import com.g5.app.models.service.ITipoService;

@Controller
@RequestMapping(value="/tipo")
public class TipoController {
	
	@Autowired
	private ITipoService tipoService;
	
	@RequestMapping(value= "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Tipo tipo = new Tipo();
		model.put("tipo", tipo);
		model.put("titulo", "Formulario de Tipo");
		return "tipo/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Tipo tipo,BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Tipo");
			return "tipo/form";
		}

		String mensajeFlash = (tipo.getId() != null) ? "Tipo editado con éxito!" : "Tipo creado con éxito!";

		tipoService.save(tipo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "tipo/listar";
	}
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Tipos");
	    model.addAttribute("tipo", tipoService.findAll());
	    return "tipo/listar";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id")Long id,Map<String, Object> model,RedirectAttributes flash) {
		Tipo tipo =null;
	    model.put("tipo", tipo);
		model.put("titulo", "Editar Tipo");
		return "tipo/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id != null) {
			tipoService.delete(id);
			flash.addFlashAttribute("success", "Tipo eliminado con éxito!");

		}
		return "tipo/listar";
	}
}
