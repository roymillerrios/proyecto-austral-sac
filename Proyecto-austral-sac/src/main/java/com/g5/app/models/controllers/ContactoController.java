package com.g5.app.models.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
	
	@GetMapping("/index")
	public String index() {
		return "contacto/index";
	}
	
	@GetMapping("/preguntas")
	public String preguntas() {
		return "contacto/preguntas";
	}
}
