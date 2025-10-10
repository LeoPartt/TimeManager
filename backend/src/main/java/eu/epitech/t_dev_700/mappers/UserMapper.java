package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = PasswordMapper.class)
public interface UserMapper extends CRUDMapper<
        UserEntity,
        UserModels.UserModel,
        UserModels.PostUserRequest,
        UserModels.PutUserRequest,
        UserModels.PatchUserRequest
        > {

    @Override
    @Mapping(target = "username", source = "account.username")
    UserModels.UserModel toModel(UserEntity entity);

    @Override
    default UserModels.UserModel[] listEntity(List<UserEntity> entities) {
        return entities.stream().map(this::toModel).toArray(UserModels.UserModel[]::new);
    }

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account.username", source = "username")
    @Mapping(target = "account.password", source = "password", qualifiedByName = "encodePassword")
    UserEntity createEntity(UserModels.PostUserRequest req);

    /*@AfterMapping
    default void attachAccount(UserModels.PostUserRequest body,
                               @MappingTarget UserEntity user) {
        var acc = new AccountEntity();
        acc.setUsername(body.username());
        user.setAccount(acc);
        acc.setUser(user);
    }*/

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    void replaceEntity(@MappingTarget UserEntity entity, UserModels.PutUserRequest body);

    @AfterMapping
    default void replaceAccountUsername(UserModels.PutUserRequest req, @MappingTarget UserEntity user) {
        AccountEntity acc = user.getAccount();
        acc.setUsername(req.username());
    }
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    void updateEntity(@MappingTarget UserEntity entity, UserModels.PatchUserRequest body);

    @AfterMapping
    default void updateAccountUsername(UserModels.PatchUserRequest req, @MappingTarget UserEntity user) {
        AccountEntity acc = user.getAccount();
        acc.setUsername(req.username());
    }

    @Component
    class PasswordMapper {
        private final PasswordEncoder encoder;
        public PasswordMapper(PasswordEncoder encoder) { this.encoder = encoder; }

        @Named("encodePassword")
        public String encodePassword(String raw) {
            if (raw == null || raw.isBlank()) return raw;
            return encoder.encode(raw);
        }
    }

}

