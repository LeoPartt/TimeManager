package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ScheduleEntity testSchedule;
    private UserEntity testUser;
    private OffsetDateTime arrivalTime;
    private OffsetDateTime departureTime;

    @BeforeEach
    void setUp() {
        // Create user with account
        AccountEntity account = new AccountEntity();
        account.setUsername("testuser");
        account.setPassword("password");

        testUser = new UserEntity();
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john@example.com");
        testUser.setPhoneNumber("+123456");
        testUser.setAccount(account);
        account.setUser(testUser);
        testUser = entityManager.persist(testUser);
        entityManager.flush();

        arrivalTime = OffsetDateTime.now().minusHours(8);
        departureTime = OffsetDateTime.now();

        testSchedule = new ScheduleEntity();
        testSchedule.setUser(testUser);
        testSchedule.setArrivalTs(arrivalTime);
        testSchedule.setDepartureTs(departureTime);
    }

    @Test
    void testSaveSchedule_shouldPersistSchedule() {
        ScheduleEntity saved = scheduleRepository.save(testSchedule);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUser()).isEqualTo(testUser);
        assertThat(saved.getArrivalTs()).isEqualTo(arrivalTime);
        assertThat(saved.getDepartureTs()).isEqualTo(departureTime);
    }

    @Test
    void testSaveSchedule_withoutDeparture_shouldSucceed() {
        testSchedule.setDepartureTs(null);

        ScheduleEntity saved = scheduleRepository.save(testSchedule);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getArrivalTs()).isNotNull();
        assertThat(saved.getDepartureTs()).isNull();
    }

    @Test
    void testFindById_whenExists_shouldReturnSchedule() {
        ScheduleEntity saved = scheduleRepository.save(testSchedule);
        entityManager.flush();

        Optional<ScheduleEntity> found = scheduleRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getUser()).isEqualTo(testUser);
    }

    @Test
    void testFindById_whenNotExists_shouldReturnEmpty() {
        Optional<ScheduleEntity> found = scheduleRepository.findById(999L);

        assertThat(found).isEmpty();
    }

    @Test
    void testFindAll_shouldReturnAllSchedules() {
        scheduleRepository.save(testSchedule);

        ScheduleEntity schedule2 = new ScheduleEntity();
        schedule2.setUser(testUser);
        schedule2.setArrivalTs(OffsetDateTime.now().minusHours(16));
        schedule2.setDepartureTs(OffsetDateTime.now().minusHours(8));
        scheduleRepository.save(schedule2);

        List<ScheduleEntity> schedules = scheduleRepository.findAll();

        assertThat(schedules).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testUpdateSchedule_shouldPersistChanges() {
        ScheduleEntity saved = scheduleRepository.save(testSchedule);
        entityManager.flush();
        entityManager.clear();

        OffsetDateTime newDeparture = OffsetDateTime.now().plusHours(1);
        saved.setDepartureTs(newDeparture);
        ScheduleEntity updated = scheduleRepository.save(saved);
        entityManager.flush();

        ScheduleEntity found = scheduleRepository.findById(updated.getId()).orElseThrow();
        assertThat(found.getDepartureTs()).isEqualTo(newDeparture);
    }

    @Test
    void testDeleteSchedule_shouldRemove() {
        ScheduleEntity saved = scheduleRepository.save(testSchedule);
        Long scheduleId = saved.getId();
        entityManager.flush();

        scheduleRepository.delete(saved);
        entityManager.flush();

        Optional<ScheduleEntity> found = scheduleRepository.findById(scheduleId);
        assertThat(found).isEmpty();
    }

    @Test
    void testSaveMultipleSchedulesForSameUser_shouldSucceed() {
        scheduleRepository.save(testSchedule);

        ScheduleEntity schedule2 = new ScheduleEntity();
        schedule2.setUser(testUser);
        schedule2.setArrivalTs(OffsetDateTime.now().minusDays(1));
        schedule2.setDepartureTs(OffsetDateTime.now().minusDays(1).plusHours(8));

        ScheduleEntity saved = scheduleRepository.save(schedule2);

        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testClockIn_onlyArrivalTime() {
        ScheduleEntity clockIn = new ScheduleEntity();
        clockIn.setUser(testUser);
        clockIn.setArrivalTs(OffsetDateTime.now());

        ScheduleEntity saved = scheduleRepository.save(clockIn);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getArrivalTs()).isNotNull();
        assertThat(saved.getDepartureTs()).isNull();
    }

    @Test
    void testClockOut_updateDepartureTime() {
        ScheduleEntity clockIn = new ScheduleEntity();
        clockIn.setUser(testUser);
        clockIn.setArrivalTs(OffsetDateTime.now().minusHours(4));
        ScheduleEntity saved = scheduleRepository.save(clockIn);
        entityManager.flush();
        entityManager.clear();

        assertThat(saved.getDepartureTs()).isNull();

        // Clock out
        saved.setDepartureTs(OffsetDateTime.now());
        ScheduleEntity updated = scheduleRepository.save(saved);
        entityManager.flush();

        ScheduleEntity found = scheduleRepository.findById(updated.getId()).orElseThrow();
        assertThat(found.getDepartureTs()).isNotNull();
        assertThat(found.getDepartureTs()).isAfter(found.getArrivalTs());
    }

    @Test
    void testSaveSchedule_withSameArrivalAndDeparture_shouldSucceed() {
        OffsetDateTime sameTime = OffsetDateTime.now();
        testSchedule.setArrivalTs(sameTime);
        testSchedule.setDepartureTs(sameTime);

        ScheduleEntity saved = scheduleRepository.save(testSchedule);

        assertThat(saved.getArrivalTs()).isEqualTo(saved.getDepartureTs());
    }

    @Test
    void testFindAllSchedulesForUser() {
        scheduleRepository.save(testSchedule);

        ScheduleEntity schedule2 = new ScheduleEntity();
        schedule2.setUser(testUser);
        schedule2.setArrivalTs(OffsetDateTime.now().minusDays(1));
        scheduleRepository.save(schedule2);

        entityManager.flush();

        List<ScheduleEntity> allSchedules = scheduleRepository.findAll();
        long userSchedules = allSchedules.stream()
                .filter(s -> s.getUser().equals(testUser))
                .count();

        assertThat(userSchedules).isGreaterThanOrEqualTo(2);
    }
}
