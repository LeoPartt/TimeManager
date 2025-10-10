package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.mappers.TeamMapper;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.repositories.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends CRUDService<
        TeamEntity,
        TeamModels.TeamModel,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.PatchTeamRequest
        > {

    protected TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        super(teamRepository, teamMapper, "TeamModel");
    }
}
