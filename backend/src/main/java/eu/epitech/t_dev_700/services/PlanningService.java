package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.PlanningEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.PlanningMapper;
import eu.epitech.t_dev_700.models.PlanningModels;
import eu.epitech.t_dev_700.repositories.PlanningRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanningService extends CRUDService<
        PlanningEntity,
        PlanningModels.PlanningResponse,
        PlanningModels.PostPlanningRequest,
        PlanningModels.PutPlanningRequest,
        PlanningModels.PatchPlanningRequest
        > {

    private final PlanningRepository planningRepository;
    private final MembershipService membershipService;

    public PlanningService(
            PlanningRepository planningRepository,
            PlanningMapper planningMapper, MembershipService membershipService) {
        super(planningRepository, planningMapper, "Planning");
        this.planningRepository = planningRepository;
        this.membershipService = membershipService;
    }

    public PlanningModels.PlanningResponse[] getForUser(UserEntity user) {
        return this.CRUDMapper.listEntity(this.planningRepository.findByUser(user));
    }

    public boolean isOwner(UserEntity user, Long planningId) {
        return this.findEntityOrThrow(planningId).getUser().equals(user);
    }

    public boolean isManagerOfOwner(UserEntity user, Long planningId) {
        return membershipService.isUserManagerOfOther(user, this.findEntityOrThrow(planningId).getUser());
    }
}
