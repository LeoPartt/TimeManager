package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.services.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@Tag(name = "Team Management")
public class TeamController extends CRUDController<
        TeamEntity,
        TeamModels.TeamModel,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.PatchTeamRequest
        > {

    public TeamController(TeamService teamService) {
        super(teamService);
    }
}
