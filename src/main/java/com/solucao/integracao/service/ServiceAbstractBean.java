package com.solucao.integracao.service;


import com.solucao.integracao.model.filter.FiltroPadrao;
import com.solucao.integracao.service.excepetion.NegocioException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@MappedSuperclass
public abstract class ServiceAbstractBean<T,F> implements ServiceBasic<T,F>, Serializable {

    private Class<T> persistentClass;

    private Class<F> classeFiltro;

    /*
    @Autowired
    private PaginacaoUtil paginacaoUtil;*/

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
/*
    @Autowired
    protected  ServiceAbstractBean( final EntityManagerFactory entityManagerFactory) {
        this.persistentClass =
                (Class<Bean>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = entityManagerFactory.createEntityManager();

    }
*/

    public abstract JpaRepository getRepository();

   // public abstract Object getFiltroPadrao();

    protected T bean;


    /*
     *
     * Estas propriedades servirão como parametros dentro dos metodos para cada uma das operações realizadas ( insert,
     * update e delete )
     * */
    private Boolean podeExcluir = Boolean.TRUE;

    private Boolean podeSalvar = Boolean.TRUE;


    @Override
    @Transactional(readOnly = false)
    public void salvar(T objeto) throws NegocioException {

        try {

            beforeSave(objeto);
            ;

            if (this.podeSalvar) {
                // spring-data o metodo save e utilizado para novas inclusões ou alterações (update)
                getRepository().save(objeto);
            }
            bean = null;
            afterSave();

        } catch (Exception e) {
            throw new NegocioException("Erro ao tentar salvar registro : " + e.getMessage());

        }

    }

    @Override
    @Transactional(readOnly = false)
    public void delete(T objeto) throws NegocioException {

        try {

            beforeRemove();

            if (this.podeExcluir) {
                getRepository().delete(objeto);
            }

            bean = null;

            afterRemove();

        } catch (Exception e) {
            throw new NegocioException("Erro ao tentar deletar registro : " + e.getMessage());

        }

    }

    @Override
    public T findById(Long id) {
        return (T) getRepository().getOne(id);
    }

    @Override
    public List<T> buscarTodos() {
        return getRepository().findAll();
    }


/*
    @Override
    @Transactional(readOnly = false)
    public Page<T> filtrar(F filtro) {

            criarEntityManagerEclasses();

            Criteria criteria =
                    entityManager.unwrap(Session.class).createCriteria(this.persistentClass);

            paginacaoUtil.preparar(criteria, pageable, ((FiltroPadrao) filtro).getOrdemDefault());
            adicionarFiltro(filtro, criteria);

        return new PageImpl<>(criteria.list(), pageable, total(filtro));
    }

*/

    @Override
    @Transactional(readOnly = false)
    public List<T> filtrar(F filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        criteria.setFirstResult( ((FiltroPadrao) filtro ).getPrimeiroRegistro());
        criteria.setMaxResults(  ((FiltroPadrao) filtro ).getQuantidadeRegistros());

        if (((FiltroPadrao) filtro ).isAscendente() &&  ((FiltroPadrao) filtro ).getPropriedadeOrdenacao() != null) {
            criteria.addOrder(Order.asc( ((FiltroPadrao) filtro ).getPropriedadeOrdenacao()));
        } else if (((FiltroPadrao) filtro ).getPropriedadeOrdenacao() != null) {
            criteria.addOrder(Order.desc(((FiltroPadrao) filtro ).getPropriedadeOrdenacao()));
        }

        return criteria.list();
    }

    public int quantidadeFiltrados(F filtro) {

        Criteria criteria = criarCriteriaParaFiltro(filtro);

        criteria.setProjection(Projections.rowCount());

        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criarCriteriaParaFiltro(F filtro) {

        criarEntityManagerEclasses();
        Session session = entityManager.unwrap(Session.class);  //manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(this.persistentClass);

        adicionarFiltro(filtro, criteria);
/*
        if (StringUtils.isNotEmpty(filtro.getDescricao())) {
            criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
        }
*/
        return criteria;
    }

 /*
    private Long total(  F   filtro) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria( this.persistentClass );
        adicionarFiltro(filtro, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }
*/
    protected abstract void adicionarFiltro(F filtro, Criteria criteria);


    /*
    *
    * Criar EntityManager e classe de <Bean>
    * Obs : É criado somente o objeto de <Bean> nulo
    *
    * */

    private void criarEntityManagerEclasses() {

        if (this.entityManager == null) {
            this.entityManager = entityManagerFactory.createEntityManager();
        }

        if (this.persistentClass == null) {
            this.persistentClass =
                    (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }

        if(this.classeFiltro == null){

           this.classeFiltro =
                   (Class<F>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        }

    }


    /*
    *
    * Cria uma instancia de <Bean>
    * */
    @SuppressWarnings("Duplicates")
    private T getNewInstanceOfBean() {
        try {
            ParameterizedType superClass =
                    (ParameterizedType) getClass()
                            .getGenericSuperclass();
            Class<T> type =
                    (Class<T>) superClass
                            .getActualTypeArguments()[0];
            return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }




    /*
     * Esse ponto é muito importante para abstração da
     * nossa classe, pois com os métodos abaixo garantimos
     * que outros códigos, com lógicas totalmente diferentes
     * possam ser adicionadas a nossa classe sem
     * que cause nenhum problema.
     * */
    public abstract void beforeRemove();

    public abstract void afterRemove();

    public abstract void beforeSave(T objeto);

    public abstract void afterSave();




}
