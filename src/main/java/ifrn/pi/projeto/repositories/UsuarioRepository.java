package ifrn.pi.projeto.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.projeto.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	
	Optional<Usuario> findByUsername(String username);
	
}
