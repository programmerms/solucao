package com.solucao.integracao.service;


import com.solucao.integracao.model.OrgMilitar;
import com.solucao.integracao.model.filter.OrgMilitarFilter;
import com.solucao.integracao.repository.OrgMilitarRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OrgMilitarService extends ServiceAbstractBean<OrgMilitar, OrgMilitarFilter>  {

    @Autowired
    private OrgMilitarRepository repositorio;


    @Override
    public JpaRepository getRepository() {
        return this.repositorio;
    }

    @Override
    protected void adicionarFiltro(OrgMilitarFilter filtro, Criteria criteria) {

    }

    @Override
    public void beforeRemove() {

    }

    @Override
    public void afterRemove() {

    }

    @Override
    public void beforeSave(OrgMilitar objeto) {

    }

    @Override
    public void afterSave() {

    }
}
