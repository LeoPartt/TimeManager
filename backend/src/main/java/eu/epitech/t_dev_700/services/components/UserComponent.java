package eu.epitech.t_dev_700.services.components;

import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import org.springframework.stereotype.Component;

@Component
public class UserComponent {

    private final UserRepository repository;

    public UserComponent(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity getUser(Long id) {
        return repository.findById(id).orElseThrow(new ResourceNotFound("user", id));
    }

}
