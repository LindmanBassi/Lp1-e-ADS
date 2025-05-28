package br.com.bassi.TrabalhoFaculLP1.e.A.controller;

import br.com.bassi.TrabalhoFaculLP1.e.A.dto.ParticipacaoEventoDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.ParticipacaoEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
