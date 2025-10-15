package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.UserMapper;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.components.UserAuthorization;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUDService<
        UserEntity,
        UserModels.UserModel,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper, "User");
        this.userMapper = userMapper;
    }

    public UserModels.UserModel getCurrentUser() {
        return userMapper.toModel(UserAuthorization.getCurrentUser());
    }
}
