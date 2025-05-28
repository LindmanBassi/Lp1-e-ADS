// Pessoa.java
package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
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
