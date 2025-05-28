package br.com.bassi.TrabalhoFaculLP1.e.A.repositories;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Evento;
import br.com.bassi.TrabalhoFaculLP1.e.A.domain.ParticipacaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipacaoEventoRepository extends JpaRepository<ParticipacaoEvento, Long> {
    int countByEvento(Evento evento);
    int countByEventoAndFuncionario(Evento evento, boolean funcionario);

}
