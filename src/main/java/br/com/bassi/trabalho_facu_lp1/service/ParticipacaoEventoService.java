package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Evento;
import br.com.bassi.trabalho_facu_lp1.domain.Local;
import br.com.bassi.trabalho_facu_lp1.domain.ParticipacaoEvento;
import br.com.bassi.trabalho_facu_lp1.dto.ParticipacaoEventoDTO;
import br.com.bassi.trabalho_facu_lp1.repositories.EventoRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.FuncionarioRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.ParticipacaoEventoRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipacaoEventoService {

    private final EventoRepository eventoRepository;
    private final ParticipacaoEventoRepository participacaoEventoRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public void adicionarParticipante(ParticipacaoEventoDTO dto) {
        Evento evento = eventoRepository.findById(dto.eventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Local local = evento.getLocal();

        if (dto.isFuncionario()) {
            if (!funcionarioRepository.existsById(dto.pessoaId())) {
                throw new RuntimeException("Funcionário não encontrado");
            }
        } else {
            if (!pessoaRepository.existsById(dto.pessoaId())) {
                throw new RuntimeException("Pessoa não encontrada");
            }
        }

        int total = participacaoEventoRepository.countByEvento(evento);
        if (total >= local.getCapacidade()) {
            throw new RuntimeException("Capacidade total do evento foi atingida");
        }

        if (dto.isFuncionario()) {
            int funcionarios = participacaoEventoRepository.countByEventoAndFuncionario(evento, true);
            int limiteFuncionarios = local.getCapacidade() - local.getNumPessoasPadrao();
            if (funcionarios >= limiteFuncionarios) {
                throw new RuntimeException("Limite de funcionários atingido");
            }
        } else {
            int pessoas = participacaoEventoRepository.countByEventoAndFuncionario(evento, false);
            if (pessoas >= local.getNumPessoasPadrao()) {
                throw new RuntimeException("Limite de pessoas comuns atingido");
            }
        }

        ParticipacaoEvento participacao = new ParticipacaoEvento();
        participacao.setEvento(evento);
        participacao.setPessoaId(dto.pessoaId());
        participacao.setFuncionario(dto.isFuncionario());

        participacaoEventoRepository.save(participacao);
    }
}
