package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.ClockModels;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clocks")
public class ClockController {

    @PostMapping("")
    public void PostClock(@Valid @RequestBody ClockModels.PostClockRequest body) {
        // TODO: Logic for clocking in
    }
}
