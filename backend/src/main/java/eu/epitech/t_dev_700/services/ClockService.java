package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.services.exceptions.ClockedOutWithNoClockedIn;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFoundException;
import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import eu.epitech.t_dev_700.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClockService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public Long[] getUserClocks(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(ResourceNotFoundException.supply("User", id));
        List<ScheduleEntity> schedules = this.scheduleRepository.findByUser(user);
        return schedules.stream().flatMap(s -> s.getDepartureTs() == null ?
                Stream.of(s.getArrivalTs().toEpochSecond()) :
                Stream.of(s.getArrivalTs().toEpochSecond(), s.getDepartureTs().toEpochSecond())
        ).toArray(Long[]::new);
    }

    public void postClock(ClockModels.PostClockRequest body) {
        UserEntity userEntity = ((AccountEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
        if (body.io() == ClockModels.ClockAction.OUT) {
            ScheduleEntity scheduleEntity = scheduleRepository
                    .findByUserAndDepartureTsIsNull(userEntity)
                    .orElseThrow(ClockedOutWithNoClockedIn::new);
            scheduleEntity.setArrivalTs(body.timestamp());
            scheduleRepository.save(scheduleEntity);
        } else if (body.io() == ClockModels.ClockAction.IN) {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setUser(userEntity);
            scheduleEntity.setArrivalTs(body.timestamp());
            scheduleRepository.save(scheduleEntity);
        }
    }

}
