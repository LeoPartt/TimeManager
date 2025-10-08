package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.UserModels;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UserController {
public final class UserController {

    @GetMapping
    public UserModels.GetUserResponse GetUsers() {
        // TODO: Logic for getting users
        return null;
    }

    @PostMapping
    public UserModels.User PostUser(@Valid @RequestBody UserModels.PostUserRequest body) {
        // TODO: Logic for creating a user
        return null;
    }

    @PutMapping("{id}")
    public void PutUser(@PathVariable String id, @Valid @RequestBody UserModels.PutUserRequest body) {
        // TODO: Logic for updating a user
    }

    @DeleteMapping("{id}")
    public void DeleteUser(@PathVariable String id) {
        // TODO: Logic for deleting a user
    }
}
