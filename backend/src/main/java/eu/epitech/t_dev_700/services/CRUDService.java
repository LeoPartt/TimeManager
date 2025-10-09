package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.controllers.exceptions.ResourceNotFoundException;
import eu.epitech.t_dev_700.mappers.CRUDMapper;
import eu.epitech.t_dev_700.utils.CRUDHookUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Generic CRUD service with mapping and template hooks.
 *
 * @param <E> Entity type
 * @param <C> Create request DTO type
 * @param <U> Update request DTO type
 * @param <M> Response model type
 */
public abstract class CRUDService<E, C, U, M> {

    protected final JpaRepository<E, Long> repository;
    protected final CRUDMapper<E, C, U, M> CRUDMapper;
    protected final String entityName;

    protected CRUDService(JpaRepository<E, Long> repository,
                          CRUDMapper<E, C, U, M> CRUDMapper,
                          String entityName) {
        this.repository = repository;
        this.CRUDMapper = CRUDMapper;
        this.entityName = entityName;
    }

    @Transactional(readOnly = true)
    public M[] list() {
        List<E> entities = repository.findAll();
        return CRUDMapper.listEntity(entities);
    }

    @Transactional(readOnly = true)
    public M get(Long id) {
        return CRUDMapper.toModel(getOrThrow(id));
    }

    @Transactional
    public M create(C request) {
        E entity = CRUDMapper.createEntity(request);
        CRUDHookUtils.beforeCreate(this, entity, request);
        E saved = repository.save(entity);
        CRUDHookUtils.afterCreate(this, saved, request);
        return CRUDMapper.toModel(saved);
    }

    @Transactional
    public M update(Long id, U request) {
        E entity = getOrThrow(id);
        CRUDMapper.updateEntity(entity, request);
        CRUDHookUtils.beforeUpdate(this, entity, request);
        E saved = repository.save(entity);
        CRUDHookUtils.afterUpdate(this, saved, request);
        return CRUDMapper.toModel(saved);
    }

    @Transactional
    public void delete(Long id) {
        E entity = getOrThrow(id);
        CRUDHookUtils.beforeDelete(this, entity);
        repository.delete(entity);
        CRUDHookUtils.afterDelete(this, entity);
    }

    protected E getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException.supply(this.entityName, id));
    }

}

