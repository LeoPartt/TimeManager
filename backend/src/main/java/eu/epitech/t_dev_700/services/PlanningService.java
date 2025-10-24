package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.PlanningEntity;
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

    public PlanningService(
            PlanningRepository planningRepository,
            PlanningMapper planningMapper) {
        super(planningRepository, planningMapper, "Planning");
    }

}
