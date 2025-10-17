package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.HasId;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * Generic CRUD service with mapping and template hooks.
 *
 * @param <M> DTO Model type
 * @param <C> DTO Create type
 * @param <R> DTO PUT type
 * @param <U> DTO Update type
 */
public interface CRUDController<M extends HasId, C, R, U> {

    ResponseEntity<M> Get(Long id);

    ResponseEntity<M[]> GetAll();

    ResponseEntity<M> Post(C body);

    ResponseEntity<M> Put(Long id, R body);

    ResponseEntity<M> Patch(Long id, U body);

    ResponseEntity<Void> Delete(Long id);

    default ResponseEntity<M> created(String path, M model) {
        return ResponseEntity.created(URI.create("/%s/%d".formatted(path, (model == null)?0:model.id()))).body(model);
    }
}
