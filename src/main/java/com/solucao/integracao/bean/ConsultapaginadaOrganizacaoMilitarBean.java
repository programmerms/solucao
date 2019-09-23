package com.solucao.integracao.bean;

import com.solucao.integracao.model.OrgMilitar;
import com.solucao.integracao.model.filter.FiltroPadrao;
import com.solucao.integracao.model.filter.OrgMilitarFilter;
import com.solucao.integracao.service.OrgMilitarService;
import com.solucao.integracao.service.ServiceAbstractBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;

@Controller
@ViewScoped
public class ConsultapaginadaOrganizacaoMilitarBean extends ConsultaPaginadaAbstractBean<OrgMilitar> {

    @Autowired
    @Getter
    private OrgMilitarService service;


    @Getter
    @Setter
    private OrgMilitarFilter filtrar = new OrgMilitarFilter();


    public ConsultapaginadaOrganizacaoMilitarBean() {

    }

    @Override
    public ServiceAbstractBean getServico() {
        return this.service;
    }

    @Override
    public FiltroPadrao getFiltro() {
        return this.filtrar ;
    }
}
