package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // ---------- Entity -> DTO ----------
    @Mapping(target = "username", source = "account.username")
    UserModels.User toModel(UserEntity entity);

    default UserModels.GetUserResponse getUsers(List<UserEntity> entities) {
        UserModels.User[] arr = entities.stream().map(this::toModel).toArray(UserModels.User[]::new);
        return new UserModels.GetUserResponse(arr);
    }

    // ---------- POST: DTO -> Entity (create) ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    UserEntity createUser(UserModels.PostUserRequest req, @Context PasswordEncoder encoder);

    @AfterMapping
    default void attachAccount(UserModels.PostUserRequest body,
                               @MappingTarget UserEntity user,
                               @Context PasswordEncoder encoder) {
        var acc = new AccountEntity();
        acc.setUsername(body.username());
        acc.setPassword(encoder.encode(body.password()));
        user.setAccount(acc);
        acc.setUser(user);
    }

    // ---------- PUT: DTO -> existing Entity (update) ----------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    void updateUser(@MappingTarget UserEntity entity, UserModels.PutUserRequest body);

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

