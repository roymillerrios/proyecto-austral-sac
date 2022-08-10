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

import com.g5.app.models.entity.UnidadMedida;
import com.g5.app.models.service.IUnidadMedidaService;

@Controller
 @RequestMapping(value="/unidadmedida")
@SessionAttributes("unidadmedida")
public class UnidadMedidaController {
	
	@Autowired
	private IUnidadMedidaService unidadmedidaService;
	
	@RequestMapping(value= "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		UnidadMedida unidadmedida = new UnidadMedida();
		model.put("unidadmedida", unidadmedida);
		model.put("titulo", "Formulario de UnidadMedida");
		return "unidadmedida/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid UnidadMedida unidadmedida,BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Unidad de Medida");
			return "unidadmedida/form";
		}

		String mensajeFlash = (unidadmedida.getId() != null) ? "Unidad de Medida editado con éxito!" : "Unidad de Medida creado con éxito!";

		unidadmedidaService.save(unidadmedida);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/unidadmedida/listar";
	}
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Unidad de Medida");
	    model.addAttribute("unidadmedida", unidadmedidaService.findAll());
	    return "unidadmedida/listar";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id")Long id,Map<String, Object> model,RedirectAttributes flash) {
		UnidadMedida unidadmedida =null;
		if (id > 0) {
			unidadmedida = unidadmedidaService.findOne(id);
			if (unidadmedida == null) {
				flash.addFlashAttribute("error", "El ID del tipo no existe en la BBDD!");
				return "redirect:/unidadmedida/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del tipo no puede ser cero!");
			return "redirect:/unidadmedida/listar";
		}
	    model.put("unidadmedida", unidadmedida);
		model.put("titulo", "Editar Unidad de Medida");
		return "unidadmedida/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id != null) {
			unidadmedidaService.delete(id);
			flash.addFlashAttribute("success", "Unidad de Medida eliminado con éxito!");

		}
		return "redirect:/unidadmedida/listar";
	}
}
