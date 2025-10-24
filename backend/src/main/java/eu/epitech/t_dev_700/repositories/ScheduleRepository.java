package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByUser(UserEntity user);

    List<ScheduleEntity> findByUserAndArrivalTsBefore(UserEntity user, OffsetDateTime arrivalTs);

    List<ScheduleEntity> findByUserAndArrivalTsAfter(UserEntity user, OffsetDateTime oneYearAgo);

    List<ScheduleEntity> findByUserAndDepartureTsAfter(UserEntity user, OffsetDateTime departureTs);

    List<ScheduleEntity> findByUserAndDepartureTsAfterAndArrivalTsBefore(UserEntity user, OffsetDateTime departureTs, OffsetDateTime arrivalTs);

    List<ScheduleEntity> findByDepartureTsIsNotNullAndArrivalTsAfter(OffsetDateTime arrivalTs);

    List<ScheduleEntity> findByUserAndDepartureTsIsNotNullAndArrivalTsAfter(UserEntity user, OffsetDateTime arrivalTs);

    List<ScheduleEntity> findByUserIdInAndDepartureTsIsNotNullAndArrivalTsAfter(Collection<Long> user_id, OffsetDateTime arrivalTs);

    Optional<ScheduleEntity> findByUserAndDepartureTsIsNull(UserEntity user);

    default ScheduleEntity createFromUserAndArrivalTs(UserEntity user, OffsetDateTime arrivalTs) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setUser(user);
        scheduleEntity.setArrivalTs(arrivalTs);
        return scheduleEntity;
    }
}
