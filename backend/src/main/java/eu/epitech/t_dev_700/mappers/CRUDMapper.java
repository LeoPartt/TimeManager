package eu.epitech.t_dev_700.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * A small mapping contract your MapStruct CRUDMapper can implement.
 * - For MapStruct, the {@code @MappingTarget} annotation on {@code updateEntity} is supported.
 */
public interface CRUDMapper<E, C, U, M> {
    M toModel(E entity);

    E createEntity(C request);

    void updateEntity(@MappingTarget E entity, U request);

    M[] listEntity(List<E> entities);
}
