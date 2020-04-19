package org.corbin.jpa.plus.base.service.impl;

import org.corbin.jpa.plus.base.repositort.BaseRepository;
import org.corbin.jpa.plus.base.service.BaseService;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 基础baseService实现类
 *
 * @author xiesu
 */
@SuppressWarnings("all")
public class BaseServiceImpl<R extends BaseRepository<T, ID>, T, ID extends Serializable> implements BaseService<T, ID> {

    @Inject
    protected R baseRepository;

    @Override
    public BaseRepository<T, ID> getBaseRepository() {
        return this.baseRepository;
    }

    /**
     * select a union domain by PK
     * if not found ,would return null
     *
     * @param id must not be {@literal null}
     * @return Retrieves an entity by its id.
     */
    @Override
    public T findById(ID id) {
        Optional<T> optional = baseRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * select a union domain by PK
     * if not found ,would return null
     *
     * @param id id is the pk
     * @return
     */
    @Override
    public T findByPK(ID id) {
        return findById(id);
    }

    /**
     * find a union a intact domain by a un-intact domain,
     * if not find ,will return null
     *
     * @param domain
     * @return
     */
    @Override
    public T findOne(T domain) {
        Assert.notNull(domain, "domain will be change type to Example ,it not be null");
        Optional<T> optional = baseRepository.findOne(Example.of(domain));
        return optional.orElse(null);
    }

    /**
     * this founction always return instance like hibernate used get   ,if not fount will be throws an exception ,
     * if you want to use 'null' to replace this exception ,please use 'findOne ' function
     * <p>
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     */
    @Override
    public T getOne(ID id) {
        Assert.notNull(id, "id must not be null");
        return baseRepository.getOne(id);
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
     *
     * @param example must not be {@literal null}.
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    @Override
    public Optional<T> findOne(Example<T> example) {
        return baseRepository.findOne(example);
    }

    /**
     * find all domain elements
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    /**
     * find all domain elements with sort
     * * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     *
     * @return
     */
    @Override
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
     */
    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return baseRepository.findAll(example, sort);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
     */
    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return baseRepository.findAll(example);
    }


    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example  must not be {@literal null}.
     * @param pageable can be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return baseRepository.findAll(example, pageable);
    }

    /**
     * returns a list domains which found by given ids
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return baseRepository.findAllById(ids);
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    @Override
    public long count(Example<T> example) {
        Assert.notNull(example, "example must not be null");
        return baseRepository.count(example);
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        baseRepository.deleteAll(entities);
    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }


    /**
     * Deletes the given entities in a batch . Assume that we will clear
     * the {@link EntityManager} after the call.
     *
     * @param entities
     */
    @Override
    public void deleteInBatch(Iterable<T> entities) {
        baseRepository.deleteInBatch(entities);

    }

    /**
     * Deletes all entities in a batch call.
     */
    @Override
    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    /**
     * save a instance
     *
     * @param domain
     * @return
     */
    @Override
    public T save(T domain) {
        return baseRepository.saveAndFlush(domain);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.CrudRepository
     */
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return baseRepository.saveAll(entities);
    }


}
