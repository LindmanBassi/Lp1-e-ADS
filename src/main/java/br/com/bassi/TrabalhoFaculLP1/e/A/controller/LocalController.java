package br.com.bassi.TrabalhoFaculLP1.e.A.controller;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Evento;
import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Local;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.EventoDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.dto.LocalDTO;
import br.com.bassi.TrabalhoFaculLP1.e.A.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locais")
@RequiredArgsConstructor
public class LocalController {
    private final LocalService localService;

    @PostMapping
    public ResponseEntity<Local> criarLocal(@RequestBody LocalDTO localDTO){
        var local = localService.criarLocal(localDTO);
        return ResponseEntity.created(URI.create("/locais/" + local.getId())).body(local);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocal(@PathVariable Long id){
        localService.deletarLocal(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Local>> listarLocais(){
        var local = localService.listarLocais();
        return ResponseEntity.ok(local);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Local>> listarLocais(@PathVariable Long id){
        var local = localService.buscarLocal(id);
        return ResponseEntity.ok(local);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Local> editarLocal(@PathVariable Long id, @RequestBody LocalDTO localDTO){
        var local = localService.editarLocal(id,localDTO);
        return ResponseEntity.ok().build();
    }
}
