package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    // ---------- Entity -> DTO ----------
    TeamModels.Team toModel(TeamEntity entity);

    default TeamModels.GetTeamResponse getTeams(List<TeamEntity> entities) {
        TeamModels.Team[] arr = entities.stream().map(this::toModel).toArray(TeamModels.Team[]::new);
        return new TeamModels.GetTeamResponse(arr);
    }

    // ---------- POST: DTO -> Entity (create) ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    TeamEntity createTeam(TeamModels.PostTeamRequest req, @Context PasswordEncoder encoder);

    // ---------- PUT: DTO -> existing Entity (update) ----------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateTeam(@MappingTarget TeamEntity entity, TeamModels.PutTeamRequest body);
}

