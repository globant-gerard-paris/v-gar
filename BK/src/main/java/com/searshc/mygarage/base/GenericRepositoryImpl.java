package com.searshc.mygarage.base;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.EntityInformation;

@NoRepositoryBean
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
	implements GenericRepository<T, ID>, Serializable {

	private static final Log log = LogFactory.getLog(GenericRepositoryImpl.class);
	
	private EntityInformation<T, ?> entityInformation;
	
	@Inject
	private EntityManager em;
	
	private Class<?> springDataRepositoryInterface;
	
	public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager, Class<?> springDataRepositoryInterface) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.em = entityManager;
		this.springDataRepositoryInterface = springDataRepositoryInterface;
	}
	
	public GenericRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		this(JpaEntityInformationSupport.getMetadata(domainClass, entityManager), entityManager, null);
	}
	
	@Override
	public <S extends T> S save(S entity) {
		if (this.entityInformation.isNew(entity)) {
			this.em.persist(entity);
			flush();
			return entity;
		}
		entity = this.em.merge(entity);
		flush();
		return entity;
	}
	
	@Override
	public List<T> findAll(Specification<T> spec) {
		return super.findAll(spec);
	}

	/**
	 * @return the springDataRepositoryInterface
	 */
	public Class<?> getSpringDataRepositoryInterface() {
		return springDataRepositoryInterface;
	}
	
	/**
	 * @param springDataRepositoryInterface the springDataRepositoryInterface to set
	 */
	public void setSpringDataRepositoryInterface(
			Class<?> springDataRepositoryInterface) {
		this.springDataRepositoryInterface = springDataRepositoryInterface;
	}

	
	
	

}
