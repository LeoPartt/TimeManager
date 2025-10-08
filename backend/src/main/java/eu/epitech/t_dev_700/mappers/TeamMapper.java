package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper extends CRUDMapper<
        TeamEntity,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.Team
        > {

    TeamModels.Team toModel(TeamEntity entity);

    default TeamModels.Team[] listEntity(List<TeamEntity> entities) {
        return entities.stream().map(this::toModel).toArray(TeamModels.Team[]::new);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    TeamEntity createEntity(TeamModels.PostTeamRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget TeamEntity entity, TeamModels.PutTeamRequest body);
}

