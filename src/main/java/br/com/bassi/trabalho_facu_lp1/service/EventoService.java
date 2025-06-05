package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Evento;
import br.com.bassi.trabalho_facu_lp1.domain.Local;
import br.com.bassi.trabalho_facu_lp1.domain.Usuario;
import br.com.bassi.trabalho_facu_lp1.dto.EventoDTO;
import br.com.bassi.trabalho_facu_lp1.repositories.EventoRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.LocalRepository;
import br.com.bassi.trabalho_facu_lp1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final LocalRepository localRepository;
    private final UsuarioRepository usuarioRepository;


    public Evento criarEvento(EventoDTO dto) {
        Evento evento = new Evento();

        evento.setNome(dto.nome());
        evento.setData(dto.data());
        evento.setEstadoEvento(dto.estadoEvento());
        evento.setTipoEvento(dto.tipoEvento());
        evento.setTitulo(dto.titulo());
        evento.setDescricao(dto.descricao());
        evento.setVagas(dto.vagas());

        if (dto.tipoEvento().equals("REMOTO")) {
            Local local = localRepository.findById(dto.localId())
                    .orElseThrow(() -> new RuntimeException("Local não encontrado"));
            evento.setLocal(local);
        }

        Usuario palestrante = usuarioRepository.findById(dto.palestranteId())
                .orElseThrow(() -> new RuntimeException("Palestrante não encontrado"));
        evento.setPalestrante(palestrante);

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
        if(eventoDTO.estadoEvento().equals("FECHADO")){
            throw new RuntimeException("Evento já foi fechado!");
        }
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        evento.setNome(eventoDTO.nome());
        evento.setTipoEvento(eventoDTO.tipoEvento());
        evento.setEstadoEvento(eventoDTO.estadoEvento());
        evento.setData(eventoDTO.data());
        evento.setTitulo(eventoDTO.titulo());
        evento.setDescricao(eventoDTO.descricao());
        evento.setVagas(eventoDTO.vagas());

        if (eventoDTO.palestranteId() != null) {
            Usuario palestrante = usuarioRepository.findById(eventoDTO.palestranteId())
                    .orElseThrow(() -> new RuntimeException("Palestrante não encontrado"));
            evento.setPalestrante(palestrante);
        }

        if (eventoDTO.tipoEvento().equals("REMOTO")) {
            evento.setLocal(null);
        } else {
            if (eventoDTO.localId() != null) {
                Local local = localRepository.findById(eventoDTO.localId())
                        .orElseThrow(() -> new RuntimeException("Local não encontrado"));
                evento.setLocal(local);
            }
        }
        return eventoRepository.save(evento);
    }

}
