package com.solucao.integracao.model;


import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class ModeloAbstrato implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract Long retornaID();


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((retornaID() == null)
                ? 0 : retornaID().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return (obj instanceof ModeloAbstrato)
                ? (this.retornaID() == null
                ? this == obj : this.retornaID().equals(((ModeloAbstrato) obj).retornaID())) : false;

    }

    public boolean isNovo() {
        boolean retorna=retornaID() == null;

        return retorna;
    }



}
