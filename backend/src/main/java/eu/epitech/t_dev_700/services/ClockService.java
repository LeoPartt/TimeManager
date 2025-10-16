package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import eu.epitech.t_dev_700.services.components.UserAuthorization;
import eu.epitech.t_dev_700.services.components.UserComponent;
import eu.epitech.t_dev_700.services.exceptions.InvalidClocking;
import eu.epitech.t_dev_700.utils.SelectorSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClockService {

    private final UserComponent userComponent;
    private final ScheduleRepository scheduleRepository;

    public Long[] getUserClocks(Long id, Optional<Long> from, Optional<Long> to) {
        UserEntity user = userComponent.getUser(id);
        return getUserClocks(new SelectorSupplier<Long, List<ScheduleEntity>>()
                .ifBoth((f, t) -> scheduleRepository.findByUserAndDepartureTsAfterAndArrivalTsBefore(user, getDT(f), getDT(t)))
                .ifLeft(f -> scheduleRepository.findByUserAndDepartureTsAfter(user, getDT(f)))
                .ifRight(t -> scheduleRepository.findByUserAndArrivalTsBefore(user, getDT(t)))
                .ifNone(() -> scheduleRepository.findByUser(user))
                .apply(from, to)
        );
    }

    public Long[] getUserClocks(List<ScheduleEntity> supplier) {
        return supplier
                .stream()
                .flatMap(scheduleEntity -> scheduleEntity.getDepartureTs() == null ?
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond()) :
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond(), scheduleEntity.getDepartureTs().toEpochSecond())
                ).toArray(Long[]::new);
    }

    public void postClock(ClockModels.PostClockRequest body) {
        this.postClock(body, UserAuthorization.getCurrentUser());
    }

    public void postClock(ClockModels.PostClockRequest body, Long id) {
        this.postClock(body, userComponent.getUser(id));
    }

    public void postClock(ClockModels.PostClockRequest body, UserEntity user) {
        switch (body.io()) {
            case IN -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(user)
                    .ifPresentOrElse(
                            new InvalidClocking(),
                            () -> scheduleRepository.save(scheduleRepository
                                    .createFromUserAndArrivalTs(user, body.timestamp())));
            case OUT -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(user)
                    .ifPresentOrElse(
                            scheduleEntity -> {
                                scheduleEntity.setDepartureTs(body.timestamp());
                                scheduleRepository.save(scheduleEntity);
                            },
                            new InvalidClocking());
        }
    }

    private static OffsetDateTime getDT(Long timestamp) {
        return OffsetDateTime.of(LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC), ZoneOffset.UTC);
    }

}
