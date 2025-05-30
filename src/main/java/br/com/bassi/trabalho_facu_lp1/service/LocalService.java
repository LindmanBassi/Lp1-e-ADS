package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.domain.Local;
import br.com.bassi.trabalho_facu_lp1.dto.LocalDTO;
import br.com.bassi.trabalho_facu_lp1.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalService {

    private final LocalRepository localRepository;

    public Local criarLocal(LocalDTO localDTO){
        Local local = new Local(localDTO);
        return localRepository.save(local);
    }

    public void deletarLocal(Long id){
        localRepository.deleteById(id);
    }

    public Optional<Local> buscarLocal(Long id){
        return localRepository.findById(id);
    }

    public List<Local> listarLocais(){
        return localRepository.findAll();
    }

    public Local editarLocal(Long id, LocalDTO localDTO){
        Optional<Local> localOptional= localRepository.findById(id);
        if(localOptional.isPresent()){
            Local local = localOptional.get();
            local.setNome(localDTO.nome());
            local.setEndereco(localDTO.endereco());
            local.setCapacidade(localDTO.capacidade());
            return localRepository.save(local);
        } else {
            throw new RuntimeException("Local nao encontrado");
        }
    }
}
