package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import eu.epitech.t_dev_700.models.PlanningModels;
import eu.epitech.t_dev_700.services.PlanningService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/plannings")
@Tag(name = "Plannings")
@ApiUnauthorizedResponse
public class PlanningController implements CRUDController<
        PlanningModels.PlanningResponse,
        PlanningModels.PostPlanningRequest,
        PlanningModels.PutPlanningRequest,
        PlanningModels.PatchPlanningRequest
        > {

    PlanningService planningService;


    @Override
    public ResponseEntity<PlanningModels.PlanningResponse> Get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PlanningModels.PlanningResponse[]> GetAll() {
        return null;
    }

    @Override
    public ResponseEntity<PlanningModels.PlanningResponse> Post(PlanningModels.PostPlanningRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<PlanningModels.PlanningResponse> Put(Long id, PlanningModels.PutPlanningRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<PlanningModels.PlanningResponse> Patch(Long id, PlanningModels.PatchPlanningRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> Delete(Long id) {
        return null;
    }
}
