package br.com.bassi.trabalho_facu_lp1.service;

import br.com.bassi.trabalho_facu_lp1.dto.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepResponseDTO buscarPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepResponseDTO response = restTemplate.getForObject(url, ViaCepResponseDTO.class);
        if (response == null || response.cep() == null) {
            throw new RuntimeException("CEP inválido ou não encontrado.");
        }
        return response;
    }
}