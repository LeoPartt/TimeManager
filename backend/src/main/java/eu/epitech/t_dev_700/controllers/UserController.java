package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.ClockService;
import eu.epitech.t_dev_700.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/users")
public class UserController extends CRUDController<
        UserEntity,
        UserModels.UserModel,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    private final UserService userService;
    private final ClockService clockService;

    public UserController(UserService userService, ClockService clockService) {
        super(userService);
        this.userService = userService;
        this.clockService = clockService;
    }

    @GetMapping("{id}/clocks")
    public OffsetDateTime[] getUserClocks(@PathVariable Long id) {
        return clockService.getUserClocks(id);
    }

    @GetMapping("/me")
    public UserModels.UserModel getUser() {
        return userService.getCurrentUser();
    }
}
