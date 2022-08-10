package com.g5.app.models.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g5.app.models.entity.Usuario;


@Controller
@RequestMapping("/login")
public class LoginController {


		@GetMapping
		public String loguerse() {
			return "login";
		}
		
		@PostMapping
		public String ingresar(Model model) {
			Usuario usuario= new Usuario();
			 model.addAttribute("titulo","Sistema Web - AUSTRAL SAC");
			 model.addAttribute("usuario",usuario);
			return "index";
		}
	


}
