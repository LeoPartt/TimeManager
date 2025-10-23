package eu.epitech.t_dev_700.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "planning")
public class PlanningEntity {

    @Id
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek DayOfWeek;

    private OffsetDateTime StartTime;
    private OffsetDateTime EndTime;

    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

}
