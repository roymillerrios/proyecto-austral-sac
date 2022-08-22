package com.g5.app.models.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g5.app.models.service.IUploadFileService;
import com.g5.app.models.entity.Rol;
import com.g5.app.models.entity.Trabajador;
import com.g5.app.util.paginator.PageRender;
import com.g5.app.models.dao.IRolDao;
import com.g5.app.models.service.IRolService;
import com.g5.app.models.service.ITrabajadorService;

@Controller
@SessionAttributes("trabajador")
@RequestMapping(value="/trabajador")
public class TrabajadorController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ITrabajadorService trabajadorService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private IRolService rolService;
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Trabajador trabajador = trabajadorService.findOne(id);
		if (trabajador == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/trabajador/listar";
		}

		model.put("trabajador", trabajador);
		model.put("titulo", "Detalle Trabajador: " + trabajador.getNombreCompleto());
		return "trabajador/ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 10);

		Page<Trabajador> trabajadores = trabajadorService.findAll(pageRequest);

		PageRender<Trabajador> pageRender = new PageRender<Trabajador>("/trabajador/listar", trabajadores);
		model.addAttribute("titulo", "Listado de trabajadores");
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("page", pageRender);
		return "trabajador/listar";
	}

	@RequestMapping(value = "/form",method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {

		Trabajador trabajador = new Trabajador();
		
		model.put("trabajador", trabajador);
		model.put("titulo", "Formulario de Trabajador");
		model.put("roles", rolService.findAll());
		return "trabajador/form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Trabajador trabajador = null;

		if (id != null) {
			trabajador = trabajadorService.findOne(id);
			if (trabajador == null) {
				flash.addFlashAttribute("error", "El ID del trabajador no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del trabajador no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("rol",trabajador.getRoles().get(0).getId());
		model.put("trabajador", trabajador);
		model.put("titulo", "Editar Trabajador");
		model.put("roles", rolService.findAll());
		return "trabajador/editar";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Trabajador trabajador, BindingResult result, Model model,
						  @RequestParam("file") MultipartFile foto,
						  @RequestParam("file1") MultipartFile antecedentesPenales,
						  @RequestParam("file2") MultipartFile antecedentesPoliciales,
						  @RequestParam("file3") MultipartFile certificadoDomiciliario,
						  @RequestParam(name = "rol_id", required = false) Long rolId,
						  RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Trabajadores");
			model.addAttribute("trabajador", trabajador);
			model.addAttribute("roles", rolService.findAll());
			if (trabajador.getId() != null && trabajador.getId() > 0 ) {
				return "trabajador/editar";
			}else{
				return "trabajador/form";
			}
		}

		if (rolId == null || rolId == 0) {
			model.addAttribute("titulo", "Formulario de Trabajadores");
			model.addAttribute("trabajador", trabajador);
			model.addAttribute("roles", rolService.findAll());
			model.addAttribute("error", "Error: Debe elegir un rol");
			if (trabajador.getId() != null && trabajador.getId() > 0 ) {
				return "trabajador/editar";
			}else{
				return "trabajador/form";
			}
		}

		if (!foto.isEmpty()) {
			if (trabajador.getId() != null && trabajador.getId() > 0 && trabajador.getFoto() != null && trabajador.getFoto().length() > 0) {
				uploadFileService.delete(trabajador.getFoto());
				}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			trabajador.setFoto(uniqueFilename);
		}
		if (!antecedentesPenales.isEmpty()) {
			if (trabajador.getId() != null && trabajador.getId() > 0 && trabajador.getAntecedentesPenales() != null && trabajador.getAntecedentesPenales().length() > 0) {
				uploadFileService.delete(trabajador.getAntecedentesPenales());
				}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(antecedentesPenales);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			trabajador.setAntecedentesPenales(uniqueFilename);
		}
		
		if (!antecedentesPoliciales.isEmpty()) {
			if (trabajador.getId() != null && trabajador.getId() > 0 && trabajador.getAntecedentesPoliciales() != null && trabajador.getAntecedentesPoliciales().length() > 0) {
				uploadFileService.delete(trabajador.getAntecedentesPoliciales());
				}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(antecedentesPoliciales);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			trabajador.setAntecedentesPoliciales(uniqueFilename);
		}
		if (!certificadoDomiciliario.isEmpty()) {
			if (trabajador.getId() != null && trabajador.getId() > 0 && trabajador.getCertificadoDomiciliario() != null && trabajador.getCertificadoDomiciliario().length() > 0) {
				uploadFileService.delete(trabajador.getCertificadoDomiciliario());
				}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(certificadoDomiciliario);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			trabajador.setCertificadoDomiciliario(uniqueFilename);
		}
		
		String mensajeFlash = "";
		if (trabajador.getId() != null && trabajador.getId() > 0 ) {
			mensajeFlash= "Trabajador editado con éxito!";
		}else{
			mensajeFlash= "Trabajador creado con éxito!";
		}

		if (trabajador.getPassword().isEmpty()){
			Trabajador trabajadorPrev = trabajadorService.findOne(trabajador.getId());
			trabajador.setPassword(trabajadorPrev.getPassword());
		}else {
			trabajador.setPassword(passwordEncoder.encode(trabajador.getPassword()));
		}
		List<Rol> roles = new ArrayList<Rol>();
		trabajador.setRoles(roles);
		Rol rol=rolService.findOne(rolId);
		trabajador.addRol(rol);
		trabajadorService.save(trabajador);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/trabajador/listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Trabajador trabajador = trabajadorService.findOne(id);

			trabajadorService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");

			if (uploadFileService.delete(trabajador.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + trabajador.getFoto() + " eliminada con exito!");
			}

		}
		return "redirect:/trabajador/listar";
	}
}
