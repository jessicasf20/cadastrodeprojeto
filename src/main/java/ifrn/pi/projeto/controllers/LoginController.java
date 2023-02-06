package ifrn.pi.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.projeto.models.Usuario;
import ifrn.pi.projeto.repositories.UsuarioRepository;



@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@RequestMapping("/form")
	public String form(Usuario usuario) {
		
		
		
		return "login";
	}
	

	@GetMapping("/login")
	public String login() {

		
		
		return "login";
	}
	@PostMapping("/login")
	public String adicionar(Usuario usuario) {
		
		System.out.println(usuario);
		ur.save(usuario);
		
		return "login";
	}
}
