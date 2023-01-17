package ifrn.pi.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.projeto.models.Projeto;
import ifrn.pi.projeto.repositories.ProjetoRepository;

@Controller
public class ProjetosController {
	
	@Autowired
	private ProjetoRepository er;
	
	@RequestMapping("/projeto/form")
	public String form() {
		return "projetos/formProjeto";
		
	}
		
		@PostMapping("/projeto")
		public String adicionar(Projeto projeto) {
			
			System.out.println(projeto);
			er.save(projeto);
		return "projetos/projeto-adicionado";
	}

}
