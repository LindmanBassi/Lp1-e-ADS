package br.com.bassi.trabalho_facu_lp1.dto;

import java.util.Date;

public record EventoDTO(String nome, Long localId, boolean isRemoto, Date data, String titulo, String descricao, int vagas, Long palestranteId) {
}
