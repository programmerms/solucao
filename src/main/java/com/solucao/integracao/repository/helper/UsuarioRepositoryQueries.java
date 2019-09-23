package com.solucao.integracao.repository.helper;


import com.solucao.integracao.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryQueries {

	public Optional<Usuario> porEmailEAtivo(String email);

	public List<String> permissoes(Usuario usuario);

}
