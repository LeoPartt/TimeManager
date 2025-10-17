package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiRoleProtected;
import eu.epitech.t_dev_700.models.ReportModels;
import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reports")
@ApiUnauthorizedResponse
public class ReportController {

    @Operation(summary = "Get time tracking reports")
    @GetMapping
    public ReportModels.GetReportResponse getReports() {
        // TODO: Logic for getting reports
        return null;
    }
}
