package br.com.bassi.trabalho_facu_lp1.controller;

import br.com.bassi.trabalho_facu_lp1.dto.ParticipacaoEventoDTO;
import br.com.bassi.trabalho_facu_lp1.service.ParticipacaoEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
