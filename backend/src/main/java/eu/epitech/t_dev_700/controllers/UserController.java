package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.ClockService;
import eu.epitech.t_dev_700.services.TeamService;
import eu.epitech.t_dev_700.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static eu.epitech.t_dev_700.services.components.UserAuthorization.getCurrentUser;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management")
public class UserController implements CRUDController<
        UserModels.UserModel,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    private final UserService userService;
    private final ClockService clockService;
    private final TeamService teamService;

    @Override
    @PreAuthorize("@userAuth.isSelfOrManager(authentication, #id)")
    @GetMapping("{id}")
    public UserModels.UserModel Get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @GetMapping
    public UserModels.UserModel[] GetAll() {
        return userService.list();
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public UserModels.UserModel Post(@Valid @RequestBody UserModels.PostUserRequest body) {
        return userService.create(body);
    }

    @Override
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PutMapping("{id}")
    public UserModels.UserModel Put(@PathVariable Long id, @Valid @RequestBody UserModels.PutUserRequest body) {
        return userService.replace(id, body);
    }

    @Override
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PatchMapping("{id}")
    public UserModels.UserModel Patch(@PathVariable Long id, @Valid @RequestBody UserModels.PatchUserRequest body) {
        return userService.update(id, body);
    }

    @Override
    @PreAuthorize("@userAuth.isManagerOfUser(authentication, #id)")
    @DeleteMapping("{id}")
    public void Delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/me")
    public UserModels.UserModel getMe() {
        return userService.getCurrentUser();
    }

    @Operation(summary = "Get user's clock records")
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @GetMapping("{id}/clocks")
    public Long[] getUserClocks(@PathVariable Long id, @RequestParam("from") Optional<Long> from, @RequestParam("to") Optional<Long> to) {
        return clockService.getUserClocks(id, from, to);
    }

    @Operation(summary = "Get current authenticated user")
    @GetMapping("{id}/teams")
    public TeamModels.TeamModel[] getUserTeams(@PathVariable Long id) {
        return teamService.getByUser(userService.findEntityOrThrow(id));
    }

}
