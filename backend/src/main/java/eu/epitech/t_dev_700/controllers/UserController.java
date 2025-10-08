package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends CRUDController<
        UserEntity,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.User
        > {

    public UserController(UserService userService) {
        super(userService);
    }
}
