package br.com.bassi.trabalho_facu_lp1.dto;

import br.com.bassi.trabalho_facu_lp1.domain.Local;
import java.util.Date;

public record EventoDTO(String nome, Local local, boolean isRemoto, Date data, String titulo, String descricao) {
}
