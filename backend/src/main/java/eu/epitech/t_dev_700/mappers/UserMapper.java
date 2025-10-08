package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends CRUDMapper<
        UserEntity,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.User
        > {

    @Mapping(target = "username", source = "account.username")
    UserModels.User toModel(UserEntity entity);

    default UserModels.User[] listEntity(List<UserEntity> entities) {
        return entities.stream().map(this::toModel).toArray(UserModels.User[]::new);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    UserEntity createEntity(UserModels.PostUserRequest req);

    @AfterMapping
    default void attachAccount(UserModels.PostUserRequest body,
                               @MappingTarget UserEntity user) {
        var acc = new AccountEntity();
        acc.setUsername(body.username());
        user.setAccount(acc);
        acc.setUser(user);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    void updateEntity(@MappingTarget UserEntity entity, UserModels.PutUserRequest body);

    @AfterMapping
    default void updateAccountUsername(UserModels.PutUserRequest req, @MappingTarget UserEntity user) {
        AccountEntity acc = user.getAccount();
        if (acc == null) {
            acc = new AccountEntity();
            acc.setUser(user);
            user.setAccount(acc);
        }
        acc.setUsername(req.username());
    }
}

