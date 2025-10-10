package eu.epitech.t_dev_700.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleEntityTest {

    private ScheduleEntity schedule;
    private UserEntity user;
    private OffsetDateTime arrivalTime;
    private OffsetDateTime departureTime;

    @BeforeEach
    void setUp() {
        schedule = new ScheduleEntity();
        schedule.setId(1L);

        user = new UserEntity();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        arrivalTime = OffsetDateTime.now().minusHours(8);
        departureTime = OffsetDateTime.now();

        schedule.setUser(user);
        schedule.setArrivalTs(arrivalTime);
        schedule.setDepartureTs(departureTime);
    }

    @Test
    void testGettersAndSetters() {
        assertThat(schedule.getId()).isEqualTo(1L);
        assertThat(schedule.getUser()).isEqualTo(user);
        assertThat(schedule.getArrivalTs()).isEqualTo(arrivalTime);
        assertThat(schedule.getDepartureTs()).isEqualTo(departureTime);
    }

    @Test
    void testScheduleWithOnlyArrival() {
        ScheduleEntity activeSchedule = new ScheduleEntity();
        activeSchedule.setId(2L);
        activeSchedule.setUser(user);
        activeSchedule.setArrivalTs(arrivalTime);

        assertThat(activeSchedule.getArrivalTs()).isEqualTo(arrivalTime);
        assertThat(activeSchedule.getDepartureTs()).isNull();
    }

    @Test
    void testScheduleWithArrivalAndDeparture() {
        assertThat(schedule.getArrivalTs()).isEqualTo(arrivalTime);
        assertThat(schedule.getDepartureTs()).isEqualTo(departureTime);
        assertThat(schedule.getDepartureTs()).isAfterOrEqualTo(schedule.getArrivalTs());
    }

    @Test
    void testSetDepartureTime() {
        ScheduleEntity newSchedule = new ScheduleEntity();
        newSchedule.setArrivalTs(arrivalTime);

        assertThat(newSchedule.getDepartureTs()).isNull();

        newSchedule.setDepartureTs(departureTime);
        assertThat(newSchedule.getDepartureTs()).isEqualTo(departureTime);
    }

    @Test
    void testRelationshipWithUser() {
        UserEntity newUser = new UserEntity();
        newUser.setId(2L);
        newUser.setFirstName("Jane");

        schedule.setUser(newUser);

        assertThat(schedule.getUser()).isEqualTo(newUser);
        assertThat(schedule.getUser().getFirstName()).isEqualTo("Jane");
    }

    @Test
    void testArrivalBeforeDeparture() {
        OffsetDateTime arrival = OffsetDateTime.now();
        OffsetDateTime departure = arrival.plusHours(8);

        schedule.setArrivalTs(arrival);
        schedule.setDepartureTs(departure);

        assertThat(schedule.getDepartureTs()).isAfter(schedule.getArrivalTs());
    }

    @Test
    void testSameArrivalAndDeparture() {
        OffsetDateTime sameTime = OffsetDateTime.now();

        schedule.setArrivalTs(sameTime);
        schedule.setDepartureTs(sameTime);

        assertThat(schedule.getDepartureTs()).isEqualTo(schedule.getArrivalTs());
    }

    @Test
    void testCreateScheduleWithMinimalData() {
        ScheduleEntity minimalSchedule = new ScheduleEntity();
        minimalSchedule.setUser(user);
        minimalSchedule.setArrivalTs(arrivalTime);

        assertThat(minimalSchedule.getUser()).isNotNull();
        assertThat(minimalSchedule.getArrivalTs()).isNotNull();
        assertThat(minimalSchedule.getDepartureTs()).isNull();
    }
}
