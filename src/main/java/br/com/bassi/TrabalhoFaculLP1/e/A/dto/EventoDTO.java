package br.com.bassi.TrabalhoFaculLP1.e.A.dto;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Local;

import javax.xml.crypto.Data;
import java.util.Date;

public record EventoDTO(String nome, Local local, boolean isRemoto, Date data, String titulo, String descricao) {
}
