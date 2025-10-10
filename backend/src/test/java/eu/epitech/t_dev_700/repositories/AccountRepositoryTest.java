package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    private AccountEntity testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new AccountEntity();
        testAccount.setUsername("testuser");
        testAccount.setPassword("hashedPassword");
    }

    @Test
    void testSaveAccount_shouldPersistAccount() {
        AccountEntity saved = accountRepository.save(testAccount);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("testuser");
        assertThat(saved.getPassword()).isEqualTo("hashedPassword");
        assertThat(saved.getFlags()).isEqualTo((byte) 0);
    }

    @Test
    void testFindById_whenExists_shouldReturnAccount() {
        AccountEntity saved = accountRepository.save(testAccount);
        entityManager.flush();

        Optional<AccountEntity> found = accountRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    void testFindById_whenNotExists_shouldReturnEmpty() {
        Optional<AccountEntity> found = accountRepository.findById(999L);

        assertThat(found).isEmpty();
    }

    @Test
    void testFindAll_shouldReturnAllAccounts() {
        accountRepository.save(testAccount);

        AccountEntity account2 = new AccountEntity();
        account2.setUsername("seconduser");
        account2.setPassword("password2");
        accountRepository.save(account2);

        List<AccountEntity> accounts = accountRepository.findAll();

        assertThat(accounts).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testUpdateAccount_shouldPersistChanges() {
        AccountEntity saved = accountRepository.save(testAccount);
        entityManager.flush();
        entityManager.clear();

        saved.setPassword("newHashedPassword");
        AccountEntity updated = accountRepository.save(saved);
        entityManager.flush();

        AccountEntity found = accountRepository.findById(updated.getId()).orElseThrow();
        assertThat(found.getPassword()).isEqualTo("newHashedPassword");
    }

    @Test
    void testDeleteAccount_shouldRemoveAccount() {
        AccountEntity saved = accountRepository.save(testAccount);
        Long accountId = saved.getId();
        entityManager.flush();

        accountRepository.delete(saved);
        entityManager.flush();

        Optional<AccountEntity> found = accountRepository.findById(accountId);
        assertThat(found).isEmpty();
    }

    @Test
    void testSaveAccount_withoutUsername_shouldThrowException() {
        testAccount.setUsername(null);

        assertThatThrownBy(() -> {
            accountRepository.save(testAccount);
            entityManager.flush();
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void testSaveAccount_withoutPassword_shouldThrowException() {
        testAccount.setPassword(null);

        assertThatThrownBy(() -> {
            accountRepository.save(testAccount);
            entityManager.flush();
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void testSaveAccount_withBlankUsername_shouldThrowException() {
        testAccount.setUsername("   ");

        assertThatThrownBy(() -> {
            accountRepository.save(testAccount);
            entityManager.flush();
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void testSaveAccount_withDuplicateUsername_shouldThrowException() {
        accountRepository.save(testAccount);
        entityManager.flush();

        AccountEntity duplicate = new AccountEntity();
        duplicate.setUsername("testuser");
        duplicate.setPassword("password");

        assertThatThrownBy(() -> {
            accountRepository.save(duplicate);
            entityManager.flush();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testSetFlags_shouldPersist() {
        testAccount.setFlags((byte) 5);
        AccountEntity saved = accountRepository.save(testAccount);
        entityManager.flush();

        AccountEntity found = accountRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getFlags()).isEqualTo((byte) 5);
    }

    @Test
    void testAccountWithUser_shouldMaintainRelationship() {
        UserEntity user = new UserEntity();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("+123456");
        user.setAccount(testAccount);

        testAccount.setUser(user);

        // Save user first to establish relationship
        entityManager.persist(user);
        entityManager.flush();
        entityManager.clear();

        AccountEntity found = accountRepository.findById(testAccount.getId()).orElseThrow();
        assertThat(found.getUser()).isNotNull();
        assertThat(found.getUser().getFirstName()).isEqualTo("John");
    }

    @Test
    void testDefaultFlagsValue() {
        AccountEntity saved = accountRepository.save(testAccount);
        assertThat(saved.getFlags()).isEqualTo((byte) 0);
    }
}
