package com.solucao.integracao.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo extends ModeloAbstrato {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gru_id")
    private Long id;

    @Getter
    @Setter
    @Column(name= "gru_nome",nullable=false, length=50)
    @NotBlank(message = "Informe um nome válido para este grupo!")
    private String nome;


    @ManyToMany
    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "gru_id"), inverseJoinColumns =
    @JoinColumn(name = "per_id"))
    @Getter
    @Setter
    private List<Permissao> permissoes;


    @Override
    public Long retornaID() {
        return this.id;
    }
}
