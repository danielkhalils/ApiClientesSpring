package io.github.danielkhalils.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data //Criando Getters, Setters e Construtores para os atributos da classe
@NoArgsConstructor //criar cliente com construtores
@AllArgsConstructor //criar cliente com construtores
@Builder //criar um builder para chamar a classe Cliente e criar um novo cliente
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadastro;

    //Método bter a data de cadastro no momento atual
    @PrePersist
    public void prepersist(){
        setDataCadastro(LocalDate.now());
    };

}
