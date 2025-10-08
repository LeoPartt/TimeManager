package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.services.CRUDService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * Generic CRUD service with mapping and template hooks.
 *
 * @param <E> Entity type
 * @param <C> Create request DTO type
 * @param <U> Update request DTO type
 * @param <M> Response model type
 */
public abstract class CRUDController<E, C, U, M> {

    private final CRUDService<E, C, U, M> crudService;

    protected CRUDController(CRUDService<E, C, U, M> crudService) {
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
    public M Put(@PathVariable Long id, @Valid @RequestBody U body) {
        return crudService.update(id, body);
    }

    @DeleteMapping("{id}")
    public void Delete(@PathVariable Long id) {
        crudService.delete(id);
    }
}
