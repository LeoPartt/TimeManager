package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.services.TeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController extends CRUDController<
        TeamEntity,
        TeamModels.PostTeamRequest,
        TeamModels.PutTeamRequest,
        TeamModels.Team
        > {

    public TeamController(TeamService teamService) {
        super(teamService);
    }
}
