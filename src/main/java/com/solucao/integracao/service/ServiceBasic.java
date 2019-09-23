package com.solucao.integracao.service;


import com.solucao.integracao.service.excepetion.NegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceBasic<T,F> {

    void salvar(T objeto) throws NegocioException;
    void delete(T objeto) throws NegocioException;
     T findById(Long id);
     List<T> buscarTodos();
    // Page<T> filtrar(F filtro);

    List<T> filtrar(F filtro);

}
