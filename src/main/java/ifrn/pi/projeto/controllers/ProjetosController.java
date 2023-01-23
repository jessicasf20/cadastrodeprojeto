package ifrn.pi.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.projeto.models.Docente;
import ifrn.pi.projeto.models.Projeto;
import ifrn.pi.projeto.repositories.DocenteRepository;
import ifrn.pi.projeto.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetosController {

	@Autowired
	private ProjetoRepository pr;
	@Autowired
	private DocenteRepository dr;

	@GetMapping("/form")
	public String form() {
		return "projetos/formProjeto";

	}

	@PostMapping
	public String adicionar(Projeto projeto) {

		System.out.println(projeto);
		pr.save(projeto);
		return "projetos/projeto-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Projeto> projetos = pr.findAll();
		ModelAndView mv = new ModelAndView("projetos/lista");
		mv.addObject("projetos", projetos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);
		
		if (opt.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;
		}

		md.setViewName("projetos/detalhes");
		Projeto projeto = opt.get();

		md.addObject("projeto", projeto);
		
		List<Docente> docentes = dr.findByProjeto(projeto);
		md.addObject("docentes", docentes);
		
		return md;
	}
	
	@PostMapping("/{idProjeto}")
	public String savarDocente(@PathVariable Long idProjeto, Docente docente) {
		
		
		System.out.println("Id do projeto: " + idProjeto);
		System.out.println(docente);
		
		Optional<Projeto> opt = pr.findById(idProjeto);
		if(opt.isEmpty()) {
			return "redirect:/projeto";
			
		}
		
		Projeto projeto = opt.get();
		docente.setProjeto(projeto);
		
		dr.save(docente);
		
		return "redirect:/projeto/{idProjeto}";
	}
}
