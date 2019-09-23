package com.solucao.integracao.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "om")
public class OrgMilitar extends ModeloAbstrato {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_om", nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @NotNull
    @Column(name = "sigla", nullable = true, length = 15)
    private String sigla;

    @Getter
    @Setter@NotBlank
    @NotNull
    @Column(name = "nome_om", nullable = true, length = 45)
    private String nomeOm;

    @Getter
    @Setter
    @Column(name = "data_criacao")
    private Date dataCadastro;

    @Override
    public Long retornaID() {
        return this.id;
    }

}
