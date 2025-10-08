package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
@RequiredArgsConstructor
public final class UserController {

    private final UserService userService;

    @GetMapping
    public UserModels.GetUserResponse GetUsers() {
        return userService.listUsers();
    }

    @PostMapping
    public UserModels.User PostUser(@Valid @RequestBody UserModels.PostUserRequest body) {
        return userService.createUser(body);
    }

    @PutMapping("{id}")
    public UserModels.User PutUser(@PathVariable Long id, @Valid @RequestBody UserModels.PutUserRequest body) {
        return userService.updateUser(id, body);
    }

    @DeleteMapping("{id}")
    public void DeleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
