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

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, UserMapper mapper, PasswordEncoder passwordEncoder) {
        super(repo, mapper, "User");
        this.passwordEncoder = passwordEncoder;
    }

    @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.CREATE)
    protected void beforeCreate(UserEntity entity, UserModels.PostUserRequest request) {
        if (entity.getAccount() != null && entity.getAccount().getPassword() != null) {
            entity.getAccount().setPassword(passwordEncoder.encode(entity.getAccount().getPassword()));
        }
    }

}
