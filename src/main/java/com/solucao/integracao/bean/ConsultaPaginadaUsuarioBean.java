package com.solucao.integracao.bean;

import com.solucao.integracao.model.Usuario;
import com.solucao.integracao.model.filter.FiltroPadrao;
import com.solucao.integracao.model.filter.UsuarioFilter;
import com.solucao.integracao.service.ServiceAbstractBean;
import com.solucao.integracao.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;

@Controller
@ViewScoped
public class ConsultaPaginadaUsuarioBean extends ConsultaPaginadaAbstractBean<Usuario> {

  //  private static final long serialVersionUID = 1L;


    public ConsultaPaginadaUsuarioBean() {
        System.out.println("passou ???");
    }



    @Autowired
    @Getter
    private UsuarioService service;

    @Getter
    @Setter
    private UsuarioFilter usuarioFilter = new UsuarioFilter();



    @Override
    public ServiceAbstractBean getServico() {
        return getService();
    }

    @Override
    public FiltroPadrao getFiltro() {
        return usuarioFilter;
    }


    public  void salvar(){
        throw  new RuntimeException("Teste de sistema de log");
    }



}
