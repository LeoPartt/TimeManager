package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import eu.epitech.t_dev_700.repositories.UserRepository;
import eu.epitech.t_dev_700.services.exceptions.InvalidClocking;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFoundException;
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
                        .orElseThrow(ResourceNotFoundException.supply("User", id)))
                .stream()
                .flatMap(scheduleEntity -> scheduleEntity.getDepartureTs() == null ?
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond()) :
                        Stream.of(scheduleEntity.getArrivalTs().toEpochSecond(), scheduleEntity.getDepartureTs().toEpochSecond())
                ).toArray(Long[]::new);
    }

    public void postClock(ClockModels.PostClockRequest body) {
        UserEntity userEntity = getCurrentUser();
        switch (body.io()) {
            case IN -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(userEntity)
                    .ifPresentOrElse(
                            InvalidClocking.consumer(),
                            () -> scheduleRepository.save(scheduleRepository
                                    .createFromUserAndArrivalTs(userEntity, body.timestamp())));
            case OUT -> scheduleRepository
                    .findByUserAndDepartureTsIsNull(userEntity)
                    .ifPresentOrElse(
                            scheduleEntity -> {
                                scheduleEntity.setDepartureTs(body.timestamp());
                                scheduleRepository.save(scheduleEntity);
                            },
                            InvalidClocking.runnable());
        }
    }

    private UserEntity getCurrentUser() {
        return ((AccountEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

    ;
}
