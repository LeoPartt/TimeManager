package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.ScheduleEntity;
import eu.epitech.t_dev_700.models.ReportModels;
import eu.epitech.t_dev_700.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final TeamService teamService;
    private final ScheduleRepository scheduleRepository;
    private final MembershipService membershipService;
    private final UserService userService;

    private final OffsetDateTime oneYearAgo = OffsetDateTime.from(LocalDateTime.now().minusYears(1));

    public ReportModels.GlobalReportResponse getGlobalReports() {
        return computeAverages(scheduleRepository.findByDepartureTsIsNotNullAndArrivalTsAfter(oneYearAgo))
                .map(avg -> new ReportModels.GlobalReportResponse(avg.weekly(), avg.monthly()))
                .orElse(new ReportModels.GlobalReportResponse(0, 0));
    }

    public ReportModels.UserReportResponse getUserReports(Long userId) {
        return computeAverages(scheduleRepository.findByUserAndDepartureTsIsNotNullAndArrivalTsAfter(
                userService.findEntityOrThrow(userId),
                oneYearAgo))
                .map(avg -> new ReportModels.UserReportResponse(avg.weekly(), avg.monthly()))
                .orElse(new ReportModels.UserReportResponse(0, 0));
    }

    public ReportModels.TeamReportResponse getTeamReports(Long teamId) {
        return computeAverages(
                scheduleRepository.findByUserIdInAndDepartureTsIsNotNullAndArrivalTsAfter(
                        membershipService.getMembershipsOfTeam(teamService.findEntityOrThrow(teamId))
                                .stream()
                                .map(m -> m.getUser().getId())
                                .toList(),
                        oneYearAgo))
                .map(avg -> new ReportModels.TeamReportResponse(avg.weekly(), avg.monthly()))
                .orElse(new ReportModels.TeamReportResponse(0, 0));
    }

    private Optional<Averages> computeAverages(List<ScheduleEntity> schedules) {
        if (schedules.isEmpty())
            return Optional.empty();

        double totalHours = schedules.stream()
                .mapToDouble(s -> Duration.between(s.getArrivalTs(), s.getDepartureTs()).toHours())
                .sum();

        long weekCount = schedules.stream()
                .map(s -> s.getArrivalTs().truncatedTo(ChronoUnit.DAYS)
                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)))
                .distinct()
                .count();

        long monthCount = schedules.stream()
                .map(s -> YearMonth.from(s.getArrivalTs()))
                .distinct()
                .count();

        float weekly = weekCount == 0 ? 0 : (float) (totalHours / weekCount);
        float monthly = monthCount == 0 ? 0 : (float) (totalHours / monthCount);

        return Optional.of(new Averages(weekly, monthly));
    }

    /**
     * Internal record for reusability
     */
    private record Averages(float weekly, float monthly) {
    }
}