package br.com.bassi.trabalho_facu_lp1.domain;

import br.com.bassi.trabalho_facu_lp1.domain.enuns.EnumCargos;
import br.com.bassi.trabalho_facu_lp1.domain.enuns.EnumDepartamento;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private String nome;

    @Enumerated(EnumType.STRING)
    private EnumDepartamento departamento;

    @Enumerated(EnumType.STRING)
    private EnumCargos cargo;
}
