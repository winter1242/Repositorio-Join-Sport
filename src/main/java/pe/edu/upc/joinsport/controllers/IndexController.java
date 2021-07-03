package pe.edu.upc.joinsport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public String inicio(Model model) {
		return "inicio";
	}
	
	@GetMapping("login")
	public String login(Model model) {
		return "login";
	}
}
