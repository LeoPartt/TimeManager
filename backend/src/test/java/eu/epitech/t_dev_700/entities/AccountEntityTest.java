package eu.epitech.t_dev_700.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountEntityTest {

    private AccountEntity account;
    private UserEntity user;

    @BeforeEach
    void setUp() {
        account = new AccountEntity();
        account.setId(1L);
        account.setUsername("johndoe");
        account.setPassword("hashedPassword");
        account.setFlags((byte) 0);

        user = new UserEntity();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        account.setUser(user);
    }

    @Test
    void testGettersAndSetters() {
        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getUsername()).isEqualTo("johndoe");
        assertThat(account.getPassword()).isEqualTo("hashedPassword");
        assertThat(account.getFlags()).isEqualTo((byte) 0);
        assertThat(account.getUser()).isEqualTo(user);
    }

    @Test
    void testDefaultFlags() {
        AccountEntity newAccount = new AccountEntity();
        assertThat(newAccount.getFlags()).isEqualTo((byte) 0);
    }

    @Test
    void testSetFlags() {
        account.setFlags((byte) 1);
        assertThat(account.getFlags()).isEqualTo((byte) 1);

        account.setFlags((byte) 127);
        assertThat(account.getFlags()).isEqualTo((byte) 127);
    }

    @Test
    void testEquals_sameId_shouldBeEqual() {
        AccountEntity account2 = new AccountEntity();
        account2.setId(1L);
        account2.setUsername("different");

        assertThat(account).isEqualTo(account2);
    }

    @Test
    void testEquals_differentId_shouldNotBeEqual() {
        AccountEntity account2 = new AccountEntity();
        account2.setId(2L);
        account2.setUsername("johndoe");

        assertThat(account).isNotEqualTo(account2);
    }

    @Test
    void testEquals_nullId_shouldNotBeEqual() {
        AccountEntity account2 = new AccountEntity();
        account2.setUsername("johndoe");

        AccountEntity account3 = new AccountEntity();
        account3.setUsername("janedoe");

        assertThat(account2).isNotEqualTo(account3);
    }

    @Test
    void testEquals_sameInstance_shouldBeEqual() {
        assertThat(account).isEqualTo(account);
    }

    @Test
    void testEquals_null_shouldNotBeEqual() {
        assertThat(account).isNotEqualTo(null);
    }

    @Test
    void testEquals_differentClass_shouldNotBeEqual() {
        assertThat(account).isNotEqualTo("Some String");
    }

    @Test
    void testHashCode_sameId_shouldHaveSameHashCode() {
        AccountEntity account2 = new AccountEntity();
        account2.setId(1L);

        assertThat(account.hashCode()).isEqualTo(account2.hashCode());
    }

    @Test
    void testHashCode_differentId_shouldHaveDifferentHashCode() {
        AccountEntity account2 = new AccountEntity();
        account2.setId(2L);

        assertThat(account.hashCode()).isNotEqualTo(account2.hashCode());
    }

    @Test
    void testToString_shouldContainKeyInformation() {
        String toString = account.toString();
        assertThat(toString).contains("Account");
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("username='johndoe'");
    }

    @Test
    void testBidirectionalRelationship() {
        UserEntity linkedUser = new UserEntity();
        linkedUser.setId(2L);

        account.setUser(linkedUser);

        assertThat(account.getUser()).isEqualTo(linkedUser);
    }
}
