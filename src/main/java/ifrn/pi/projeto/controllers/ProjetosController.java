package ifrn.pi.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.projeto.models.Projeto;
import ifrn.pi.projeto.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetosController {

	@Autowired
	private ProjetoRepository er;

	@GetMapping("/form")
	public String form() {
		return "projetos/formProjeto";

	}

	@PostMapping
	public String adicionar(Projeto projeto) {

		System.out.println(projeto);
		er.save(projeto);
		return "projetos/projeto-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Projeto> projetos = er.findAll();
		ModelAndView mv = new ModelAndView("projetos/lista");
		mv.addObject("projetos", projetos);
		return mv;
	}

	@GetMapping("/lista")
	public String lista() {
		return "projetos/lista";

	}
}
