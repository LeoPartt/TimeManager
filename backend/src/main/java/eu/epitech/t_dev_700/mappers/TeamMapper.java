package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper extends CRUDMapper<
        TeamEntity,
        TeamModels.TeamModel,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.PatchTeamRequest
        > {

    @Override
    TeamModels.TeamModel toModel(TeamEntity entity);

    @Override
    default TeamModels.TeamModel[] listEntity(List<TeamEntity> entities) {
        return entities.stream().map(this::toModel).toArray(TeamModels.TeamModel[]::new);
    }

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    TeamEntity createEntity(TeamModels.PostTeamRequest req);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void replaceEntity(@MappingTarget TeamEntity entity, TeamModels.PutTeamRequest body);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget TeamEntity entity, TeamModels.PatchTeamRequest body);
}

