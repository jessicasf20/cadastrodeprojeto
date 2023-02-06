package ifrn.pi.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.projeto.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	Usuario findByUsername(String username);
	
}
