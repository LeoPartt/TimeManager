package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.exceptions.InvalidClocking;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClockService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public Long[] getUserClocks(Long id, Long from, Long to) {
        return getUserClocks(
                (from == 0 && to == 0) ?
                    () -> scheduleRepository.findByUser(this.getUser(id)) :
                (from == 0) ?
                    () -> scheduleRepository.findByUserAndArrivalTsBefore(this.getUser(id), getDT(to)) :
                (to == 0) ?
                    () -> scheduleRepository.findByUserAndDepartureTsAfter(this.getUser(id), getDT(from)) :
                    () -> scheduleRepository.findByUserAndDepartureTsAfterAndArrivalTsBefore(this.getUser(id), getDT(from), getDT(to)));
    }

    public Long[] getUserClocks(Supplier<List<ScheduleEntity>> supplier) {
        return supplier.get()
                .stream()
                .flatMap(scheduleEntity -> scheduleEntity.getDepartureTs() == null ?
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond()) :
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond(), scheduleEntity.getDepartureTs().toEpochSecond())
                ).toArray(Long[]::new);
    }

    public void postClock(ClockModels.PostClockRequest body) {
        switch (body.io()) {
            case IN -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(UserService.currentUser())
                    .ifPresentOrElse(
                            new InvalidClocking(),
                            () -> scheduleRepository.save(scheduleRepository
                                    .createFromUserAndArrivalTs(UserService.currentUser(), body.timestamp())));
            case OUT -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(UserService.currentUser())
                    .ifPresentOrElse(
                            scheduleEntity -> {
                                scheduleEntity.setDepartureTs(body.timestamp());
                                scheduleRepository.save(scheduleEntity);
                            },
                            new InvalidClocking());
        }
    }

    private UserEntity getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(new ResourceNotFound("User", id));
    }

    private static OffsetDateTime getDT(Long timestamp) {
        return OffsetDateTime.of(LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC), ZoneOffset.UTC);
    }

}
