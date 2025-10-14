package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.services.ClockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clocks")
@RequiredArgsConstructor
@Tag(name = "Clock Management")
public class ClockController {

    private final ClockService clockService;

    @Operation(summary = "Record clock event")
    @PostMapping
    public void PostClock(@Valid @RequestBody ClockModels.PostClockRequest body) {
        this.clockService.postClock(body);
    }

}
