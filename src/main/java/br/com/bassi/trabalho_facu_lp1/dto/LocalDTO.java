package br.com.bassi.trabalho_facu_lp1.dto;

import br.com.bassi.trabalho_facu_lp1.domain.Endereco;

public record LocalDTO(String nome, Endereco endereco,int capacidade, int numPessoasPadrao) {
}
