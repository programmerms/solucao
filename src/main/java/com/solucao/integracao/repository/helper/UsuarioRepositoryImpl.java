package com.solucao.integracao.repository.helper;



import com.solucao.integracao.model.Usuario;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl  implements UsuarioRepositoryQueries  {

	@PersistenceContext
	@Getter
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

}
