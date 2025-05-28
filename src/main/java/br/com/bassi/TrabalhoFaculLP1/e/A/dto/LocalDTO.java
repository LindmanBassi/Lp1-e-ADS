package br.com.bassi.TrabalhoFaculLP1.e.A.dto;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Endereco;

public record LocalDTO(String nome, Endereco endereco,int capacidade, int numPessoasPadrao) {
}
