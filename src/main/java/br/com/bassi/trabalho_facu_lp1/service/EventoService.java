package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Evento;
import br.com.bassi.trabalho_facu_lp1.dto.EventoDTO;
import br.com.bassi.trabalho_facu_lp1.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public Evento criarEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento(eventoDTO);
        return eventoRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    public Optional<Evento> buscarEvento(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento editarEvento(Long id, EventoDTO eventoDTO) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setNome(eventoDTO.nome());
            evento.setLocal(eventoDTO.local());
            evento.setData(eventoDTO.data());
            evento.setRemoto(evento.isRemoto());
            evento.setTitulo(eventoDTO.titulo());
            evento.setDescricao(eventoDTO.descricao());
            return eventoRepository.save(evento);
        } else {
            throw new RuntimeException("Evento nao encontrado");
        }
    }

}
