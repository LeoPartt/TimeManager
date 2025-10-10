package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.ClockService;
import eu.epitech.t_dev_700.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @GetMapping("{id}")
    public UserModels.UserModel Get(@PathVariable Long id) {
        return userService.get(id);
    }

    @Override
    @GetMapping
    public UserModels.UserModel[] GetAll() {
        return userService.list();
    }

    @Override
    @PostMapping
    public UserModels.UserModel Post(@Valid @RequestBody UserModels.PostUserRequest body) {
        return userService.create(body);
    }

    @Override
    @PutMapping("{id}")
    public UserModels.UserModel Put(@PathVariable Long id, @Valid @RequestBody UserModels.PutUserRequest body) {
        return userService.replace(id, body);
    }

    @Override
    @PatchMapping("{id}")
    public UserModels.UserModel Patch(@PathVariable Long id, @Valid @RequestBody UserModels.PatchUserRequest body) {
        return userService.update(id, body);
    }

    @Override
    @DeleteMapping("{id}")
    public void Delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @Operation(summary = "Get user's clock records")
    @GetMapping("{id}/clocks")
    public Long[] getUserClocks(@PathVariable Long id, @RequestParam("from") Long from, @RequestParam("to") Long to) {
        return clockService.getUserClocks(id, from, to);
    }

    @Operation(summary = "Get current authenticated user")
    @GetMapping("/me")
    public UserModels.UserModel getUser() {
        return userService.getCurrentUser();
    }

}
