package com.solucao.integracao.service;

import com.solucao.integracao.model.Usuario;
import com.solucao.integracao.model.filter.UsuarioFilter;
import com.solucao.integracao.repository.UsuarioRepository;
import com.solucao.integracao.service.excepetion.NegocioException;
import com.solucao.integracao.service.excepetion.SenhaObrigatoriaUsuarioException;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService extends ServiceAbstractBean<Usuario, UsuarioFilter>  {

    @Autowired
    private UsuarioRepository repositorio;

/*
    @Autowired
    private PasswordEncoder passwordEncoder;

*/
    @Override
    public JpaRepository getRepository() {
        return this.repositorio;
    }


    @Override
    protected void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {

        if (filtro != null) {

            if (!StringUtils.isEmpty(filtro.getNome() )) {
                criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE ));
            }

            if (!StringUtils.isEmpty(filtro.getEmail() )) {
                criteria.add(Restrictions.eq("email", filtro.getEmail() ).ignoreCase() );
            }

        }

    }


    @Override
    public void beforeRemove() {

    }

    @Override
    public void afterRemove() {

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void beforeSave(Usuario objeto) {

        Optional<Usuario> usuarioExistente = repositorio.findByEmail( objeto.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new NegocioException("E-mail já cadastrado");
        }

        if (objeto.isNovo() && StringUtils.isEmpty(objeto.getSenha())) {
            throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
        }
/*
        if (objeto.isNovo()) {
            objeto.setSenha(this.passwordEncoder.encode(objeto.getSenha()));
            objeto.setConfirmacaoSenha(objeto.getSenha());
        }
*/
    }

    @Override
    public void afterSave() {

    }
}
