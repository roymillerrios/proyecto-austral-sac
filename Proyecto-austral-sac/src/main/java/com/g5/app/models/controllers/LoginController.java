package com.g5.app.models.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g5.app.models.entity.Trabajador;


@Controller
@RequestMapping("/login")
public class LoginController {


		@GetMapping
		public String IniciarSesion() {
			return "login";
		}

	


}
