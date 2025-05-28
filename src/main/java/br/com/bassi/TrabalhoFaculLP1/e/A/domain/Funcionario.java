// Funcionario.java
package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.enuns.EnumCargos;
import br.com.bassi.TrabalhoFaculLP1.e.A.domain.enuns.EnumDepartamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
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
