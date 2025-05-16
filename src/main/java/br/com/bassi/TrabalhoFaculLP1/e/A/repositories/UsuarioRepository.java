package br.com.bassi.TrabalhoFaculLP1.e.A.repositories;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}
