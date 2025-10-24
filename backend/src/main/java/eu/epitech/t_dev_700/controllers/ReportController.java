package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import eu.epitech.t_dev_700.models.ReportModels;
import eu.epitech.t_dev_700.services.ReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reports")
@ApiUnauthorizedResponse
@RequiredArgsConstructor
public class ReportController {

    private final ReportsService reportsService;

    @Operation(summary = "Get time tracking reports")
    @GetMapping
    public ResponseEntity<ReportModels.GlobalReportResponse> getReports() {
        return ResponseEntity.ok(reportsService.getGlobalReports());
    }

    @Operation(summary = "Get time tracking reports")
    @GetMapping("users/{id}")
    public ResponseEntity<ReportModels.UserReportResponse> getUserReports(@PathVariable Long id) {
        return ResponseEntity.ok(reportsService.getUserReports(id));
    }

    @Operation(summary = "Get time tracking reports")
    @GetMapping("teams/{id}")
    public ResponseEntity<ReportModels.TeamReportResponse> getTeamReports(@PathVariable Long id) {
        return ResponseEntity.ok(reportsService.getTeamReports(id));
    }
}
