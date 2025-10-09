package eu.epitech.t_dev_700.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

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

    E createEntity(C request);

    void replaceEntity(@MappingTarget E entity, R request);
    void updateEntity(@MappingTarget E entity, U request);

    M[] listEntity(List<E> entities);
}
