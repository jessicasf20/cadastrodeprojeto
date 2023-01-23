package ifrn.pi.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.projeto.models.Docente;
import ifrn.pi.projeto.models.Projeto;

public interface DocenteRepository extends JpaRepository<Docente, Long>{
	
	List<Docente> findByProjeto(Projeto projeto);
	
}
