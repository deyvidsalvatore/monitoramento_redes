package io.github.deyvidsantos.monitoramento_redes.models;

import io.github.deyvidsantos.monitoramento_redes.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servidor {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "O endereço IP não pode ser vázio ou nulo")
    private String enderecoIp;


    private String nome;
    private String memoria;
    private String tipo;
    private String imageUrl;
    private Status status;
}
