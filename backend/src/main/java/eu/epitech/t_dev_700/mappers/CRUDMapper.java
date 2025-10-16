package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Stream;

/**
 * A small mapping contract your MapStruct CRUDMapper can implement.
 * - For MapStruct, the {@code @MappingTarget} annotation on {@code updateEntity} is supported.
 * @param <E> Entity type
 * @param <M> DTO Model type
 * @param <C> DTO Create type
 * @param <R> DTO PUT type
 * @param <U> DTO Update type
 */
public interface CRUDMapper<E, M, C, R, U> {
    M toModel(E entity);

    M[] listEntity(List<E> entities);
    M[] listEntity(Stream<E> stream);

    E createEntity(C request);
    void replaceEntity(@MappingTarget E entity, R request);

    void updateEntity(@MappingTarget E entity, U request);
}
