package br.com.bassi.trabalho_facu_lp1.repositories;

import br.com.bassi.trabalho_facu_lp1.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
