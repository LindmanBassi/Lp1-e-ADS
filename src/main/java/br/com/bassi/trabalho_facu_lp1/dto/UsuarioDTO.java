package br.com.bassi.trabalho_facu_lp1.dto;

import br.com.bassi.trabalho_facu_lp1.domain.enuns.EnumCargos;
import br.com.bassi.trabalho_facu_lp1.domain.enuns.EnumDepartamento;

public record UsuarioDTO(String nome, String email, String senha, Boolean isFuncionario, EnumCargos cargo, EnumDepartamento departamento
) {}
