package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import br.com.bassi.TrabalhoFaculLP1.e.A.dto.EventoDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Evento")
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    public Evento(EventoDTO eventoDTO) {
        this.nome = eventoDTO.nome();
        this.local = eventoDTO.local();
    }
}
