package br.com.wnfa.alurachallenge.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.repository.custom.ExpenseRepositoryCustom;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;

public class ExpenseRepositoryCustomImpl implements ExpenseRepositoryCustom{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ExpenseDO> FindExpenses(ExpenseFilter expenseFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ExpenseDO> criteria = builder.createQuery(ExpenseDO.class);
        Root<ExpenseDO> root = criteria.from(ExpenseDO.class);

        Predicate[] predicates = criarRestricoes(expenseFilter, builder, root);
        criteria.where(predicates);
//                .groupBy(root.get("description"));

        TypedQuery<ExpenseDO> query = manager.createQuery(criteria);

        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(expenseFilter));
	}
	
	/**
	 * Método responsável por criar as restrições de acordo com o filtro passado no parametro
	 * @param expenseFilter filtros utilizados para realizar a consulta
	 * @param builder construir consultas para banco de dados
	 * @param root entidade que representa a tabela no banco de dados
	 * @return resultado com filtros dinamicos
	 */
	private Predicate[] criarRestricoes(ExpenseFilter expenseFilter, CriteriaBuilder builder, Root<ExpenseDO> root) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (!ObjectUtils.isEmpty(expenseFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get("description")), "%" + expenseFilter.getDescription().toLowerCase() + "%"
            ));

        }
        if (!ObjectUtils.isEmpty(expenseFilter.getCategory())) {
        	predicates.add(builder.equal(
        			root.get("category"), expenseFilter.getCategory()
        			));			
		}
        return predicates.toArray(new Predicate[predicates.size()]);
    }
	
	private Long total(ExpenseFilter expenseFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ExpenseDO> root = criteria.from(ExpenseDO.class);

        Predicate[] predicates = criarRestricoes(expenseFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<ExpenseDO> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegstroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegstroPorPagina);
    }
}
