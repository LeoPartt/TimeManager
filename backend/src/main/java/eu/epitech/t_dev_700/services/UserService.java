package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.UserMapper;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserService(UserRepository repo, UserMapper mapper) {
        super(repo, mapper, "User");
        this.userMapper = mapper;
    }

    public UserModels.UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return userMapper.toModel(user);
    }
}
