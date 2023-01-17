package ifrn.pi.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.projeto.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
