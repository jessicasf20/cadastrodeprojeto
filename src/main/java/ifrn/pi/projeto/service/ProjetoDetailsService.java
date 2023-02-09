package ifrn.pi.projeto.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ifrn.pi.projeto.models.Usuario;
import ifrn.pi.projeto.repositories.UsuarioRepository;

@Service
public class ProjetoDetailsService implements UserDetailsService {

final UsuarioRepository usuarioRepository;

public ProjetoDetailsService (UsuarioRepository usuarioRepository) {
this.usuarioRepository = usuarioRepository;
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Usuario usuario = usuarioRepository.findByUsername(username)
.orElseThrow(() -> new UsernameNotFoundException(username));

return new usuario(usuario.getUsername(), usuario.getPassword(), enabled: true, accountNonExoired: true, credentialsNonExpired: true, usuario.getAuthorities());
}

}
