package br.com.bassi.trabalho_facu_lp1.controller;

import br.com.bassi.trabalho_facu_lp1.domain.Evento;
import br.com.bassi.trabalho_facu_lp1.dto.EventoDTO;
import br.com.bassi.trabalho_facu_lp1.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody EventoDTO eventoDTO) {
        var evento = eventoService.criarEvento(eventoDTO);
        return ResponseEntity.created(URI.create("/eventos/" + evento.getId())).body(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        var evento = eventoService.listarEventos();
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Evento>> listarEvento(@PathVariable Long id) {
        var evento = eventoService.buscarEvento(id);
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> editarEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        var evento = eventoService.editarEvento(id, eventoDTO);
        return ResponseEntity.ok().build();
    }


}
