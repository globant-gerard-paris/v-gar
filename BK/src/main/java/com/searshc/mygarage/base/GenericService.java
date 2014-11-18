package com.searshc.mygarage.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("rawtypes")
public abstract class GenericService<E, T extends Serializable, K extends GenericRepository> {

    protected static final Log log = LogFactory.getLog(GenericService.class);

    @Inject
    protected K repository;

    protected int defaultPageSize = 10;

    protected Sort defaultPageSort = new Sort(Sort.Direction.ASC, "id");

    protected Pageable constructPageSpecification(int pageIndex, int pageSize, Sort pageSort) {
        if (pageSize == 0) {
            pageSize = this.defaultPageSize;
        }
        if (pageSort == null) {
            pageSort = this.defaultPageSort;
        }
        Pageable pageSpecification = new PageRequest(pageIndex, pageSize, pageSort);
        return pageSpecification;
    }

    @SuppressWarnings("unchecked")
    public E getItem(T id) {

        return (E) repository.findOne(id);
    }

    @SuppressWarnings("unchecked")
    public List<E> getList() {

        List<E> items = repository.findAll();
        return items;
    }

    @SuppressWarnings("unchecked")
    public Page<E> getList(int pageIndex, int pageSize, Sort sort) {

        if (pageIndex < 0) {
            pageIndex = 0;
        }
        Page requestedPage = repository.findAll(constructPageSpecification(pageIndex, pageSize, sort));

        return requestedPage;
    }

    @SuppressWarnings("unchecked")
    public List<E> getList(Sort sort) {

        List<E> items = repository.findAll(sort);
        return items;
    }

    @SuppressWarnings("unchecked")
    public Page<E> getList(Pageable pageable) {

        Page<E> items = repository.findAll(pageable);
        return items;
    }

    @SuppressWarnings("unchecked")
    public List<E> getList(Specification spec) {

        List<E> items = repository.findAll(spec);
        return items;
    }

    @SuppressWarnings("unchecked")
    public List<E> getList(Specification spec, Sort sort) {

        List<E> items = repository.findAll(spec, sort);
        return items;
    }

    public Set<E> save(Set<E> items) {

        Set<E> saved = new HashSet<E>();
        for (E e : items) {
            saved.add(save(e));
        }
        return saved;
    }

    public Set<E> saveAndFlush(Set<E> items) {

        Set<E> saved = new HashSet<E>();
        for (E e : items) {
            saved.add(save(e));
        }
        flush();
        return saved;
    }

    @SuppressWarnings("unchecked")
    public E save(E item) {

        return (E) repository.save(item);
    }

    @SuppressWarnings("unchecked")
    public E saveAndFlush(E item) {

        return (E) repository.saveAndFlush(item);

    }

    public void flush() {

        repository.flush();
    }

}
