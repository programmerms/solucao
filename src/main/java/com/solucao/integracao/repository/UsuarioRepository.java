package com.solucao.integracao.repository;


import com.solucao.integracao.model.Usuario;
import com.solucao.integracao.repository.helper.UsuarioRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries {

    public Optional<Usuario> findByEmail(String email);


}
