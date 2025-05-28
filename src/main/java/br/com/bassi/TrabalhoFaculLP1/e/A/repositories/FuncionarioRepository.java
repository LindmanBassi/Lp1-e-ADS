package br.com.bassi.TrabalhoFaculLP1.e.A.repositories;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
