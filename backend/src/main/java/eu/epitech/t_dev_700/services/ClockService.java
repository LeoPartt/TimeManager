package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.exceptions.InvalidClocking;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClockService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public Long[] getUserClocks(Long id) {
        return scheduleRepository
                .findByUser(userRepository
                        .findById(id)
                        .orElseThrow(new ResourceNotFound("User", id)))
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

}
