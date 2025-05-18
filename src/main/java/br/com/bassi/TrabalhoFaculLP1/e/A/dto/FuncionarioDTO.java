package br.com.bassi.TrabalhoFaculLP1.e.A.dto;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.enuns.EnumCargos;
import br.com.bassi.TrabalhoFaculLP1.e.A.domain.enuns.EnumDepartamento;

public record FuncionarioDTO(
        String nome,
        String email,
        String senha,
        EnumDepartamento departamento,
        EnumCargos cargo
) {}

