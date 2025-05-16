package br.com.bassi.TrabalhoFaculLP1.e.A.repositories;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
