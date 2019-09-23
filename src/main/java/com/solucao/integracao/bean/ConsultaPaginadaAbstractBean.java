package com.solucao.integracao.bean;

import com.solucao.integracao.model.filter.FiltroPadrao;
import com.solucao.integracao.service.ServiceAbstractBean;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class ConsultaPaginadaAbstractBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract ServiceAbstractBean getServico();

    public abstract FiltroPadrao getFiltro();

    private LazyDataModel<T> model;


    public ConsultaPaginadaAbstractBean() {

        model = new LazyDataModel<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<T> load(int first, int pageSize,
                                String sortField, SortOrder sortOrder,
                                Map<String, Object> filters) {

                getFiltro().setPrimeiroRegistro(first);
                getFiltro().setQuantidadeRegistros(pageSize);
                getFiltro().setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                getFiltro().setPropriedadeOrdenacao(sortField);

                setRowCount(getServico().quantidadeFiltrados( getFiltro() ));

                return getServico().filtrar( getFiltro() );
            }


        };

    }

    public LazyDataModel<T> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }
}
