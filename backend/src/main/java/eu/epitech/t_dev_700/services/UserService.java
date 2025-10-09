package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.UserMapper;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.utils.CRUDHookUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUDService<
        UserEntity,
        UserModels.PostUserRequest, UserModels.PutUserRequest,
        UserModels.User> {

    public UserService(UserRepository repo, UserMapper mapper) {
        super(repo, mapper, "User");
    }

}
