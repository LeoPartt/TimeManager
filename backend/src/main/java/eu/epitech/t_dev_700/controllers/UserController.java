package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @Override
    @PreAuthorize("@userAuth.isSelfOrManager(authentication, #id)")
    @GetMapping("{id}")
    public ResponseEntity<UserModels.UserModel> Get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @GetMapping
    public ResponseEntity<UserModels.UserModel[]> GetAll() {
        return ResponseEntity.ok(userService.list());
    }

    @Override
    @PreAuthorize("@userAuth.isManager(authentication)")
    @PostMapping
    public ResponseEntity<UserModels.UserModel> Post(@Valid @RequestBody UserModels.PostUserRequest body) {
        return this.created("users", userService.create(body));
    }

    @Override
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PutMapping("{id}")
    public ResponseEntity<UserModels.UserModel> Put(@PathVariable Long id, @Valid @RequestBody UserModels.PutUserRequest body) {
        return ResponseEntity.ok(userService.replace(id, body));
    }

    @Override
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @PatchMapping("{id}")
    public ResponseEntity<UserModels.UserModel> Patch(@PathVariable Long id, @Valid @RequestBody UserModels.PatchUserRequest body) {
        return ResponseEntity.ok(userService.update(id, body));
    }

    @Override
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get current authenticated user")
    @GetMapping("/me")
    public UserModels.UserModel getMe() {
        return userService.getCurrentUser();
    }

    @Operation(summary = "Get user's clock records")
    @PreAuthorize("@userAuth.isSelfOrManagerOfUser(authentication, #id)")
    @GetMapping("{id}/clocks")
    public ResponseEntity<Long[]> getUserClocks(@PathVariable Long id, @RequestParam("from") Optional<Long> from, @RequestParam("to") Optional<Long> to) {
        return ResponseEntity.ok(this.userService.getClocks(id, from, to));
    }

    @Operation(summary = "Get user's teams")
    @GetMapping("{id}/teams")
    public ResponseEntity<TeamModels.TeamModel[]> getTeams(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getTeams(id));
    }

}
