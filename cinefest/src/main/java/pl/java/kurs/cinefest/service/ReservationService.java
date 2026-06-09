package pl.java.kurs.cinefest.service;

import lombok.RequiredArgsConstructor;
import pl.java.kurs.cinefest.staff.CinemaStaff;
import pl.java.kurs.cinefest.model.FestivalTicket;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class ReservationService {
    private final String paymentSystem;

    public void processReservation(CinemaStaff staff, FestivalTicket ticket) {
        CinemaStaff chosenStaff = Optional.ofNullable(staff)
                .filter(s -> s.isOnDuty() && s.getSkillLevel() >= 3)
                .orElseGet(() -> CinemaStaff.builder()
                        .name("Domyślny Bileter Rezerwowy")
                        .skillLevel(3)
                        .onDuty(true)
                        .build());

        Optional.ofNullable(chosenStaff).ifPresentOrElse(
                s -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(s.getName());
                    sb.append(ticket.getSeat());
                    sb.reverse();
                    sb.append((int) (Math.random() * 1000) + 1000);

                    String code = sb.toString();
                    sb.setLength(0);

                    System.out.println("Pomyślnie zweryfikowano! Unikalny kod: " + code);
                },
                () -> {
                    throw new NoSuchElementException("Brak pracownika!");
                }
        );
    }
}
