package br.com.bassi.trabalho_facu_lp1.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Pessoa")
@Table(name = "pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private String nome;
}
