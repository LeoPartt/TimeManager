package eu.epitech.t_dev_700.mappers;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.UserModels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserEntity userEntity;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setUsername("johndoe");
        accountEntity.setPassword("hashedPassword");

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setPhoneNumber("+1234567890");
        userEntity.setAccount(accountEntity);
        accountEntity.setUser(userEntity);
    }

    @Test
    void testToModel_shouldMapEntityToModel() {
        UserModels.UserResponse model = userMapper.toModel(userEntity);

        assertThat(model).isNotNull();
        assertThat(model.id()).isEqualTo(1L);
        assertThat(model.username()).isEqualTo("johndoe");
        assertThat(model.firstName()).isEqualTo("John");
        assertThat(model.lastName()).isEqualTo("Doe");
        assertThat(model.email()).isEqualTo("john.doe@example.com");
        assertThat(model.phoneNumber()).isEqualTo("+1234567890");
    }

    @Test
    void testListEntity_shouldMapEntitiesToModels() {
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane@example.com");
        user2.setPhoneNumber("+0987654321");

        AccountEntity account2 = new AccountEntity();
        account2.setId(2L);
        account2.setUsername("janesmith");
        account2.setPassword("password");
        user2.setAccount(account2);

        List<UserEntity> entities = Arrays.asList(userEntity, user2);

        UserModels.UserResponse[] models = userMapper.listEntity(entities);

        assertThat(models).hasSize(2);
        assertThat(models[0].id()).isEqualTo(1L);
        assertThat(models[0].firstName()).isEqualTo("John");
        assertThat(models[1].id()).isEqualTo(2L);
        assertThat(models[1].firstName()).isEqualTo("Jane");
    }

    @Test
    void testCreateEntity_shouldMapRequestToEntity() {
        UserModels.PostUserRequest request = new UserModels.PostUserRequest(
                "newuser",
                "plainPassword",
                "Alice",
                "Wonder",
                "alice@example.com",
                "+1112223333"
        );

        UserEntity entity = userMapper.createEntity(request);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNull(); // Not set by mapper
        assertThat(entity.getFirstName()).isEqualTo("Alice");
        assertThat(entity.getLastName()).isEqualTo("Wonder");
        assertThat(entity.getEmail()).isEqualTo("alice@example.com");
        assertThat(entity.getPhoneNumber()).isEqualTo("+1112223333");
        assertThat(entity.getAccount()).isNotNull();
        assertThat(entity.getAccount().getUsername()).isEqualTo("newuser");
        assertThat(entity.getAccount().getPassword()).isNotBlank();
        // Verify password is encoded
        assertThat(passwordEncoder.matches("plainPassword", entity.getAccount().getPassword())).isTrue();
    }

    @Test
    void testCreateEntity_withNullPassword_shouldNotEncode() {
        UserModels.PostUserRequest request = new UserModels.PostUserRequest(
                "newuser",
                null,
                "Alice",
                "Wonder",
                "alice@example.com",
                "+1112223333"
        );

        UserEntity entity = userMapper.createEntity(request);

        assertThat(entity.getAccount()).isNotNull();
        assertThat(entity.getAccount().getPassword()).isNull();
    }

    @Test
    void testCreateEntity_withBlankPassword_shouldNotEncode() {
        UserModels.PostUserRequest request = new UserModels.PostUserRequest(
                "newuser",
                "   ",
                "Alice",
                "Wonder",
                "alice@example.com",
                "+1112223333"
        );

        UserEntity entity = userMapper.createEntity(request);

        assertThat(entity.getAccount()).isNotNull();
        assertThat(entity.getAccount().getPassword()).isEqualTo("   ");
    }

    @Test
    void testReplaceEntity_shouldUpdateAllFields() {
        UserModels.PutUserRequest request = new UserModels.PutUserRequest(
                "updatedjohn",
                "UpdatedJohn",
                "UpdatedDoe",
                "updated@example.com",
                "+9999999999"
        );

        userMapper.replaceEntity(userEntity, request);

        assertThat(userEntity.getFirstName()).isEqualTo("UpdatedJohn");
        assertThat(userEntity.getLastName()).isEqualTo("UpdatedDoe");
        assertThat(userEntity.getEmail()).isEqualTo("updated@example.com");
        assertThat(userEntity.getPhoneNumber()).isEqualTo("+9999999999");
        assertThat(userEntity.getAccount().getUsername()).isEqualTo("updatedjohn");
    }

    @Test
    void testUpdateEntity_shouldUpdateOnlyProvidedFields() {
        UserModels.PatchUserRequest request = new UserModels.PatchUserRequest(
                "newusername",
                "Jane",
                null,
                "newemail@example.com",
                null
        );

        String originalLastName = userEntity.getLastName();
        String originalPhone = userEntity.getPhoneNumber();

        userMapper.updateEntity(userEntity, request);

        assertThat(userEntity.getFirstName()).isEqualTo("Jane"); // Updated
        assertThat(userEntity.getLastName()).isEqualTo(originalLastName); // Not updated
        assertThat(userEntity.getEmail()).isEqualTo("newemail@example.com"); // Updated
        assertThat(userEntity.getPhoneNumber()).isEqualTo(originalPhone); // Not updated
        assertThat(userEntity.getAccount().getUsername()).isEqualTo("newusername"); // Updated
    }

    @Test
    void testUpdateEntity_withAllNulls_shouldNotChangeEntity() {
        UserModels.PatchUserRequest request = new UserModels.PatchUserRequest(
                null,
                null,
                null,
                null,
                null
        );

        String originalFirstName = userEntity.getFirstName();
        String originalLastName = userEntity.getLastName();
        String originalEmail = userEntity.getEmail();
        String originalPhone = userEntity.getPhoneNumber();

        userMapper.updateEntity(userEntity, request);

        assertThat(userEntity.getFirstName()).isEqualTo(originalFirstName);
        assertThat(userEntity.getLastName()).isEqualTo(originalLastName);
        assertThat(userEntity.getEmail()).isEqualTo(originalEmail);
        assertThat(userEntity.getPhoneNumber()).isEqualTo(originalPhone);
    }

    @Test
    void testUpdateEntity_withUsername_shouldUpdateUsername() {
        UserModels.PatchUserRequest request = new UserModels.PatchUserRequest(
                "newusername",
                null,
                null,
                null,
                null
        );

        userMapper.updateEntity(userEntity, request);

        assertThat(userEntity.getAccount().getUsername()).isEqualTo("newusername");
    }
}
