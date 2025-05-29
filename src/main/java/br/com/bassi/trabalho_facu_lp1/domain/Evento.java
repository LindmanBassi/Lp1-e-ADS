package br.com.bassi.trabalho_facu_lp1.domain;

import br.com.bassi.trabalho_facu_lp1.dto.EventoDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

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
    private boolean isRemoto;
    private Date data;
    private String descricao;
    private String titulo;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    public Evento(EventoDTO eventoDTO) {
        this.nome = eventoDTO.nome();
        this.local = eventoDTO.local();
        this.data = eventoDTO.data();
        this.isRemoto = eventoDTO.isRemoto();
        this.titulo = eventoDTO.titulo();
        this.descricao = eventoDTO.descricao();
    }
}
