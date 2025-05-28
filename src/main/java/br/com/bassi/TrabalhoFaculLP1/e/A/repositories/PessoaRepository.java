package br.com.bassi.TrabalhoFaculLP1.e.A.repositories;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);
}
