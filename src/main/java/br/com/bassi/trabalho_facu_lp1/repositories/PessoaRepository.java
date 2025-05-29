package br.com.bassi.trabalho_facu_lp1.repositories;

import br.com.bassi.trabalho_facu_lp1.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);
}
