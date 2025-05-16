package br.com.bassi.TrabalhoFaculLP1.e.A.service;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Evento;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.EventoDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public Evento criarEvento(EventoDTO eventoDTO){
        Evento evento =new Evento(eventoDTO);
        return eventoRepository.save(evento);

    }

    public void deletarEvento(Long id){
         eventoRepository.deleteById(id);

    }
    public Optional<Evento> buscarEvento(Long id){
        return eventoRepository.findById(id);

    }

    public List<Evento> listarEventos(){
       return eventoRepository.findAll();

    }

    public Evento editarEvento(Long id, EventoDTO eventoDTO){
        Optional<Evento> eventoOptional= eventoRepository.findById(id);
        if(eventoOptional.isPresent()){
            Evento evento = eventoOptional.get();
            evento.setNome(eventoDTO.nome());
            evento.setLocal(eventoDTO.local());
           return eventoRepository.save(evento);
        }
        else{
            throw new RuntimeException("Evento nao encontrado");
        }

    }

}
