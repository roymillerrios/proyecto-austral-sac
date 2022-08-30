package com.g5.app.models.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.g5.app.models.entity.*;
import com.g5.app.models.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g5.app.models.dao.IInventarioDao;
import com.g5.app.models.dao.ITipoDao;
import com.g5.app.models.dao.IUnidadMedidaDao;
import com.g5.app.util.paginator.PageRender;

@Controller
@SessionAttributes("material")
@RequestMapping(value="/material")
public class MaterialController {

	@Autowired 
	private IMaterialService materialService;
	
	@Autowired
	private IInventarioService inventarioService;
	
	@Autowired
	private ITipoDao tipoDao;
	
	@Autowired
	private IUnidadMedidaDao unidadmedidaDao;
	
	@Autowired
	private IInventarioDao inventarioDao;

	@Autowired
	private ITipoService tipoService;

	@Autowired
	private IUnidadMedidaService unidadMedidaService;

	@Autowired
	private IItemInventarioService itemInventarioService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<Material> material = materialService.findAll(pageRequest);
		PageRender<Material> pageRender = new PageRender<Material>("/material/listar", material);
		model.addAttribute("titulo", "Listado de Materiales");
		model.addAttribute("material", material);
		model.addAttribute("page", pageRender);
		return "material/listar";
	}
	@RequestMapping(value= "/form/{inventarioId}", method = RequestMethod.GET)
	public String crear(@PathVariable(value = "inventarioId") Long inventarioId, Map<String, Object> model,
			RedirectAttributes flash) {
	
		Inventario inventario = inventarioService.findOne(inventarioId);
		
		List<Tipo> listaTipo = tipoDao.findAll();
		List<UnidadMedida> listaUnidadMedida = unidadmedidaDao.findAll();
		
		model.put("titulo", "Formulario de Material");
		model.put("tipo",listaTipo);
		model.put("unidadmedida",listaUnidadMedida);
		model.put("inventario",inventario);
		return "material/form";
	
	}
	
	@RequestMapping(value= "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	
		Material material = new Material();
		List<Tipo> listaTipo = tipoDao.findAll();
		List<UnidadMedida> listaUnidadMedida = unidadmedidaDao.findAll();
		List<Inventario> listaInventario = inventarioDao.findAll();
	
		if(id != null) {
			material = materialService.findOne(id);
			if (material == null) {
				flash.addFlashAttribute("error", "El codigo del material no existe en la BBDD!");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El codigo del material no puede estar vacio");
			return "redirect:/listar";
		}
		model.put("material", material);
		model.put("titulo", "Editar Material");
		model.put("tipo",listaTipo);
		model.put("unidadmedida",listaUnidadMedida);
		model.put("inventario",listaInventario);
		return "material/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(Model model,
						  @RequestParam("nombre") String nombre,
						  @RequestParam("marca") String marca,
						  @RequestParam("stock") Integer stock,
						  @RequestParam("unidadMedida_id") Long uniMedidaId,
						  @RequestParam("tipo_id") Long tipoId,
						  @RequestParam("inventarioId") Long inventarioId,
						  RedirectAttributes flash, SessionStatus status) {
		Tipo tipo= tipoService.findOne(tipoId);
		UnidadMedida unidadMedida=unidadMedidaService.findOne(uniMedidaId);
		Material material=new Material();
		material.setMarca(marca);
		material.setNombre(nombre);
		material.setTipo(tipo);
		material.setUnidadMedida(unidadMedida);
		materialService.save(material);

		Inventario inventario=inventarioService.findOne(inventarioId);
		ItemInventario itemInventario=new ItemInventario();
		itemInventario.setMaterial(material);
		itemInventario.setStock(stock);
		itemInventario.setInventario(inventario);
		itemInventarioService.save(itemInventario);


		status.setComplete();
		flash.addFlashAttribute("success", "Se guardo con exito");
		return "redirect:/inventario/ver/" + inventario.getId();
	}

	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		ItemInventario itemMaterial = itemInventarioService.findOne(id);
		
		if (itemMaterial != null) {
			materialService.delete(id);
			flash.addFlashAttribute("success", "Material eliminado con éxito!");
			return "redirect:/inventario/ver/" + itemMaterial.getInventario().getId();
		}
		flash.addFlashAttribute("error", "El material no existe en la base de datos");
		
		return "redirect:/inventario/listar";
	}
}
