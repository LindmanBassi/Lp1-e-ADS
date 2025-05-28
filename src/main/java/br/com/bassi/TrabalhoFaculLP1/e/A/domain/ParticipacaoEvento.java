package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participacoes_evento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ParticipacaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    private Long pessoaId;
    private boolean funcionario;
}
