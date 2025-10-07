package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.ReportModels;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/reports")
public class ReportController {

    @GetMapping
    public ReportModels.GetReportResponse getReports() {
        // TODO: Logic for getting reports
        return null;
    }
}
