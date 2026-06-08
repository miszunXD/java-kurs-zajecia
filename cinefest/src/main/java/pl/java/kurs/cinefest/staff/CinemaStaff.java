package pl.java.kurs.cinefest.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CinemaStaff {
    private String name;
    private int skillLevel;
    @Builder.Default
    boolean onDuty = true;
}
