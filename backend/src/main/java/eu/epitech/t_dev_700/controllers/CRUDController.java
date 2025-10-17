package eu.epitech.t_dev_700.controllers;

/**
 * Generic CRUD service with mapping and template hooks.
 *
 * @param <M> DTO Model type
 * @param <C> DTO Create type
 * @param <R> DTO PUT type
 * @param <U> DTO Update type
 */
public interface CRUDController<M, C, R, U> {

    M Get(Long id);

    M[] GetAll();

    M Post(C body);

    M Put(Long id, R body);

    M Patch(Long id, U body);

    void Delete(Long id);
}
