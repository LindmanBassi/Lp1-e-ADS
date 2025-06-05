package br.com.bassi.trabalho_facu_lp1.controller;

import br.com.bassi.trabalho_facu_lp1.dto.ParticipacaoEventoDTO;
import br.com.bassi.trabalho_facu_lp1.dto.FuncionarioDTO;
import br.com.bassi.trabalho_facu_lp1.service.ParticipacaoEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/participacoes")
public class ParticipacaoEventoController {

    private final ParticipacaoEventoService service;

    public ParticipacaoEventoController(ParticipacaoEventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> participar(@RequestBody ParticipacaoEventoDTO dto) {
        service.adicionarParticipante(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{eventoId}/usuarios")
    public ResponseEntity<List<FuncionarioDTO>> listarUsuariosPorEvento(@PathVariable Long eventoId) {
        List<FuncionarioDTO> usuarios = service.listarUsuariosPorEvento(eventoId);
        return ResponseEntity.ok(usuarios);
    }
}
