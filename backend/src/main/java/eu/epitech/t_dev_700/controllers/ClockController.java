package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.services.ClockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Record clock event for user")
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PostMapping("/{id}")
    public void PostClock(@PathVariable Long id, @Valid @RequestBody ClockModels.PostClockRequest body) {
        this.clockService.postClock(body, id);
    }

}
