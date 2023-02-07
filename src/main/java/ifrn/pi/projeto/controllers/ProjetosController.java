package ifrn.pi.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import ifrn.pi.projeto.models.Docente;
import ifrn.pi.projeto.models.Projeto;
import ifrn.pi.projeto.repositories.DocenteRepository;
import ifrn.pi.projeto.repositories.ProjetoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/projeto")
public class ProjetosController {

	@Autowired
	private ProjetoRepository pr;
	@Autowired
	private DocenteRepository dr;

	@GetMapping("/form")
	public String form(Projeto projeto) {
		return "projetos/formProjeto";

	}

	@PostMapping
	public String salvar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attributes ) {

		if (result.hasErrors()) {
			return form(projeto);

		}

		System.out.println(projeto);
		pr.save(projeto);
		attributes.addFlashAttribute("mensagem", "Projeto adicionado com sucesso!");
		return "redirect:/projeto";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Projeto> projetos = pr.findAll();
		ModelAndView mv = new ModelAndView("projetos/lista");
		mv.addObject("projetos", projetos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Docente docente) {
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
	public ModelAndView savarDocente(@PathVariable Long idProjeto, @Valid Docente docente, BindingResult result, RedirectAttributes attributes) {
		ModelAndView md = new ModelAndView();

		if (result.hasErrors()) {

			return detalhar(idProjeto, docente);
		}

		System.out.println("Id do projeto: " + idProjeto);
		System.out.println(docente);

		Optional<Projeto> opt = pr.findById(idProjeto);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/projeto");

			return md;

		}

		Projeto projeto = opt.get();
		docente.setProjeto(projeto);

		dr.save(docente);
		attributes.addFlashAttribute("mensagem", "Docente adicionado com sucesso!");
		md.setViewName("redirect:/projeto/{idProjeto}");

		return md;
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarProjeto(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;
		}
		Projeto projeto = opt.get();
		md.setViewName("projetos/formProjeto");
		md.addObject("projeto", projeto);

		return md;
	}

	@GetMapping("/{idProjeto}/docentes/{idDocente}/selecionar")
	public ModelAndView selecionarDocente(@PathVariable Long idProjeto, @PathVariable Long idDocente) {
		ModelAndView md = new ModelAndView();

		Optional<Projeto> optProjeto = pr.findById(idProjeto);
		Optional<Docente> optDocente = dr.findById(idDocente);

		if (optProjeto.isEmpty() || optDocente.isEmpty()) {
			md.setViewName("redirect:/projetos");
			return md;
		}

		Projeto projeto = optProjeto.get();
		Docente docente = optDocente.get();

		if (projeto.getId() != docente.getProjeto().getId()) {
			md.setViewName("redirect:/projetos");
			return md;

		}

		md.setViewName("projetos/detalhes");
		md.addObject("docente", docente);
		md.addObject("projeto", projeto);
		md.addObject("docentes", dr.findByProjeto(projeto));

		return md;
	}

	@GetMapping("/{id}/remover")

	public String apagarProjeto(@PathVariable Long id, RedirectAttributes attributes) {
		

		Optional<Projeto> opt = pr.findById(id);

		if (!opt.isEmpty()) {
			Projeto projeto = opt.get();

			List<Docente> docentes = dr.findByProjeto(projeto);

			dr.deleteAll(docentes);
			pr.delete(projeto);



			attributes.addFlashAttribute("mensagem", "Projeto removido com sucesso!");
		}

		return "redirect:/projeto";
	}

	@GetMapping("/{idProjeto}/docentes/{idDocente}/remover")

	public String apagarProjeto(@PathVariable Long idProjeto, @PathVariable Long idDocente, RedirectAttributes attributes) {
		

		Optional<Docente> opt = dr.findById(idDocente);

		if (!opt.isEmpty()) {
			Docente docente = opt.get();
			dr.delete(docente);



			attributes.addFlashAttribute("mensagem", "Docente removido com sucesso!");
			

		}

		return "redirect:/projeto/{idProjeto}";
	}

}
