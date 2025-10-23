package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.doc.ApiErrorResponse;
import eu.epitech.t_dev_700.doc.ApiUnauthorizedResponse;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.services.ClockService;
import eu.epitech.t_dev_700.services.exceptions.InvalidClocking;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clocks")
@RequiredArgsConstructor
@ApiUnauthorizedResponse
@Tag(name = "Clock Management")
public class ClockController {

    private final ClockService clockService;

    @Operation(summary = "Record clock event")
    @ApiResponse(responseCode = "204", description = "Successfully clocked in / out")
    @ApiErrorResponse(InvalidClocking.class)
    @PostMapping
    public ResponseEntity<Void> PostClock(@Valid @RequestBody ClockModels.PostClockRequest body) {
        this.clockService.postClock(body);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Record clock event for user")
    @ApiResponse(responseCode = "204", description = "Successfully clocked in / out")
    @ApiErrorResponse({InvalidClocking.class, ResourceNotFound.class})
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PostMapping("/{id}")
    public ResponseEntity<Void> PostClock(@PathVariable Long id, @Valid @RequestBody ClockModels.PostClockRequest body) {
        this.clockService.postClock(body, id);
        return ResponseEntity.noContent().build();
    }

}
