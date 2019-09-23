package com.solucao.integracao.model.filter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class FiltroPadrao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int primeiroRegistro;

    @Getter
    @Setter
    private int quantidadeRegistros;

    @Getter
    @Setter
    private String propriedadeOrdenacao;

    @Getter
    @Setter
    private boolean ascendente;

    public abstract String getOrdemDefault();


}
