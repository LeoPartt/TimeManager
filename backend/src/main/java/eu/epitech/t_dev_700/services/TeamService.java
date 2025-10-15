package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.MembershipEntity;
import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
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

    private final TeamMapper teamMapper;
    private final MembershipService membershipService;

    protected TeamService(TeamRepository teamRepository, TeamMapper teamMapper, MembershipService membershipService) {
        super(teamRepository, teamMapper, "TeamModel");
        this.teamMapper = teamMapper;
        this.membershipService = membershipService;
    }

    public TeamModels.TeamModel[] getByUser(UserEntity user) {
        return teamMapper.listEntity(membershipService.getMembershipsOf(user).stream().map(MembershipEntity::getTeam));
    }
}
