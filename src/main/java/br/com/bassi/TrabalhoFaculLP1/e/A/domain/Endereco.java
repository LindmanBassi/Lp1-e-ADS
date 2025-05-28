package br.com.bassi.TrabalhoFaculLP1.e.A.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private int numero;




}
