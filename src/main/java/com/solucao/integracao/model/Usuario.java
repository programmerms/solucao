package com.solucao.integracao.model;


import com.solucao.integracao.AtributoConfirmacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios" )
@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha", message = "Confirmação da senha " +
        "não confere !!")
public class Usuario extends ModeloAbstrato {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;


    @Getter
    @Setter
    @Column(name= "nome_usuario",nullable=false, length=60)
    @NotBlank(message = "Informe um nome válido para este usuário!")
    private String nome;


    @Getter
    @Setter
    @Column(name = "email")
    @NotBlank(message = "E-mail é obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    @Getter
    @Setter
    @Column(name= "senha")
    private String senha;
/*
    @Getter
    @Setter
    @Column(name= "usu_ativo",nullable=false)
    private Boolean ativo;

    @Getter
    @Setter
    @Size(min = 1, message = "Selecione pelo menos um grupo")
    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usu_id")
            , inverseJoinColumns = @JoinColumn(name = "gru_id"))
    private List<Grupo> grupos;

    @Getter
    @Setter
    @NotNull(message = "Data de nascimento é obrigatório")
    @Column(name = "usu_dta_nascimento")
    private LocalDate dataNascimento;
*/

    @Getter
    @Setter
    @Transient
    private String confirmacaoSenha;


    @Override
    public Long retornaID() {
        return this.id;
    }
/*

    public Boolean grupoJaSeleciodado(Grupo grupo){
        boolean retorno = Boolean.FALSE;

        if ( this.getGrupos()!=null && !this.getGrupos().isEmpty()){

            for ( Grupo grupoJaSelecionado  :this.getGrupos() ) {

                if (grupoJaSelecionado.getId() == grupo.getId() ){
                    retorno = Boolean.TRUE;
                    break;
                }


            }

        }

        return retorno;

    }

*/
}
