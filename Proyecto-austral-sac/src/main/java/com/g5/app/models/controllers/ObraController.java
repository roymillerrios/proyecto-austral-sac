package com.g5.app.models.controllers;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g5.app.models.entity.ItemInventario;
import com.g5.app.models.entity.ItemTrabajador;
import com.g5.app.models.entity.ItemsObra;
import com.g5.app.models.entity.Material;
import com.g5.app.models.entity.Obra;
import com.g5.app.models.entity.Trabajador;
import com.g5.app.models.service.IItemsObraService;
import com.g5.app.models.service.IObraService;
import com.g5.app.models.service.ITrabajadorService;

@Controller
@RequestMapping(value="/obra")
@SessionAttributes("obra")
public class ObraController {
	
	@Autowired
	private IObraService obraService;
	
	@Autowired
	private ITrabajadorService trabajadorService;
	
	@Autowired
	private IItemsObraService itemsObraService;
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Obra obra =obraService.findOne(id);
		if(obra == null) {
			flash.addFlashAttribute("error", "La obra no existe en la base de datos");
			return "redirect:obra/listar";
		}
		model.put("obra", obra);
		model.put("titulo", "Detalle Obra: "+ obra.getDescripcion());
		return "obra/ver";
	}
	
	@GetMapping(value = "/cargar-trabajador/{term}", produces = { "application/json" })
	public @ResponseBody List<Trabajador> cargarTrabajadores(@PathVariable String term) {

	    return trabajadorService.findByNombreCompleto(term);
	}
	
	@RequestMapping(value= "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		
		Obra obra = new Obra();
		
		model.put("obra", obra);
		model.put("titulo", "Formulario de Obra");
		return "obra/form";
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
		if (id != null) {
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
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Obra obra,BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("obra",obra);
			model.addAttribute("titulo", "Formulario de Obra");
			return "obra/form";
		}
        
		String mensajeFlash = "";
		if(obra.getId() != null && obra.getId()>0) {
			mensajeFlash= "Obra editada con éxito!";
		}else{
			mensajeFlash= "Obra creada con éxito!";
		}
		obraService.save(obra);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/obra/listar";
	}
	
	@RequestMapping(value = "/asignar/{id}",method = RequestMethod.GET)
	public String asignar(@PathVariable(value = "id") Long id, Map<String, Object> model,
						  RedirectAttributes flash) {

		Obra obra = null;

		if (id != null) {
			obra = obraService.findOne(id);
			if (obra == null) {
				flash.addFlashAttribute("error", "El ID de la obra no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return "redirect:/listar";
		}

		model.put("obra", obra);
		model.put("titulo", "Editar Obra");
		return "obra/asignar";
	}
	
	@RequestMapping(value = "/asignar", method = RequestMethod.POST)
	public String guardarItemsObra(Model model,
						@RequestParam(name = "item_trabajador_id[]", required = false) Long[] itemId,
						@RequestParam(name = "trabajadorId", required = false) Long trabajadorId,
						@RequestParam(name = "obraId", required = false) Long obraId,
						RedirectAttributes flash, SessionStatus status) {
		Obra obra = null;

		if (obraId != null) {
			obra = obraService.findOne(obraId);
			if (obra == null) {
				flash.addFlashAttribute("error", "El ID de la obra no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la obra no puede ser cero!");
			return "redirect:/listar";
		}

		if (itemId == null || itemId.length == 0) {
		flash.addFlashAttribute("error", "Error: La lista NO puede no tener líneas!");
		
		model.addAttribute("obra", obra);
		model.addAttribute("titulo", "Formulario de Obra");
		return "redirect:/obra/asignar/"+obra.getId();
		}
		for (int i = 0; i < itemId.length; i++) {
            ItemsObra itemObra=new ItemsObra();
            Trabajador trabajador= trabajadorService.findOne(itemId[i]);
            itemObra.setObra(obra);
            itemObra.setTrabajador(trabajador);
            itemsObraService.save(itemObra);
        }
		return "redirect:/obra/listar";
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
