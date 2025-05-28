package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import br.com.bassi.TrabalhoFaculLP1.e.A.dto.LocalDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Local")
@Table(name = "locais")

public class Local {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int capacidade;
    private int numPessoasPadrao;
    @Embedded
    private Endereco endereco;

    public Local(LocalDTO localDTO) {
        this.nome = localDTO.nome();
        this.endereco = localDTO.endereco();
        this.capacidade = localDTO.capacidade();
        this.numPessoasPadrao = localDTO.numPessoasPadrao();
    }
}
