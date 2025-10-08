package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.controllers.exceptions.ResourceNotFoundException;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.mappers.UserMapper;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserModels.GetUserResponse listUsers() {
        return userMapper.getUsers(userRepository.findAll());
    }

    public UserModels.User createUser(UserModels.PostUserRequest body) {
        UserEntity entity = userMapper.createUser(body, passwordEncoder);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toModel(saved);
    }

    public UserModels.User updateUser(Long id, UserModels.PutUserRequest body) {
        UserEntity entity = getUserEntityOrThrow(id);
        userMapper.updateUser(entity, body);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toModel(saved);
    }

    public void deleteUser(Long id) {
        userRepository.delete(getUserEntityOrThrow(id));
    }

    private UserEntity getUserEntityOrThrow(Long id) throws ResourceNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException.supply("User", id));
    }
}
