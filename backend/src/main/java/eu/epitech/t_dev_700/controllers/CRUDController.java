package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.services.CRUDService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * Generic CRUD service with mapping and template hooks.
 *
 * @param <E> Entity type
 * @param <M> DTO Model type
 * @param <C> DTO Create type
 * @param <R> DTO PUT type
 * @param <U> DTO Update type
 */
public abstract class CRUDController<E, M, C, R, U> {

    private final CRUDService<E, M, C, R, U> crudService;

    protected CRUDController(CRUDService<E, M, C, R, U> crudService) {
        this.crudService = crudService;
    }

    @GetMapping("{id}")
    public M Get(@PathVariable Long id) {
        return crudService.get(id);
    }

    @GetMapping
    public Object GetAll() {
        return crudService.list();
    }

    @PostMapping
    public M Post(@Valid @RequestBody C body) {
        return crudService.create(body);
    }

    @PutMapping("{id}")
    public M Put(@PathVariable Long id, @Valid @RequestBody R body) {
        return crudService.replace(id, body);
    }

    @PatchMapping("{id}")
    public M Patch(@PathVariable Long id, @Valid @RequestBody U body) {
        return crudService.update(id, body);
    }

    @DeleteMapping("{id}")
    public void Delete(@PathVariable Long id) {
        crudService.delete(id);
    }
}
