package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.UserMapper;
import eu.epitech.t_dev_700.models.PlanningModels;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.components.UserAuthorization;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends CRUDService<
        UserEntity,
        UserModels.UserResponse,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    private final UserMapper userMapper;
    private final TeamService teamService;
    private final ClockService clockService;
    private final PlanningService planningService;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            TeamService teamService,
            ClockService clockService,
            PlanningService planningService) {
        super(userRepository, userMapper, "User");
        this.userMapper = userMapper;
        this.teamService = teamService;
        this.clockService = clockService;
        this.planningService = planningService;
    }

    @Transactional(readOnly = true)
    public UserModels.UserResponse getCurrentUser() {
        return userMapper.toModel(UserAuthorization.getCurrentUser());
    }

    @Transactional(readOnly = true)
    public TeamModels.TeamResponse[] getTeams(Long id) {
        return teamService.getByUser(this.findEntityOrThrow(id));
    }

    @Transactional(readOnly = true)
    public Long[] getClocks(Long id, Optional<Long> from, Optional<Long> to) {
        return clockService.getUserClocks(id, from, to);
    }

    public PlanningModels.PlanningResponse[] getPlannings(Long id) {
        return planningService.getForUser(this.findEntityOrThrow(id));
    }

    public PlanningModels.PlanningResponse createPlanning(PlanningModels.PostPlanningRequest body) {
        return planningService.create(body);
    }
}
