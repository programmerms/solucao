package com.solucao.integracao.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "permissao" )
public class Permissao extends ModeloAbstrato  {


    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;



    @Column(name= "per_nome",nullable=false, length=60)
    @NotBlank(message = "Informe um nome válido para esta permissão !")
    @Getter
    @Setter
    private String nome;



    @Override
    public Long retornaID() {
        return this.id;
    }
}
