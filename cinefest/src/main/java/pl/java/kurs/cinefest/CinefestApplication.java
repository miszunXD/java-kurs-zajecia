package pl.java.kurs.cinefest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.java.kurs.cinefest.staff.CinemaStaff;

@SpringBootApplication
public class CinefestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinefestApplication.class, args);

		CinemaStaff staff = new CinemaStaff("Jan", 4, false);
		modifyStaff(staff);
		System.out.println(staff.isOnDuty());
		// Modyfikujemy obiekt, ale odwołujemy się do tego samego adresu jak oryginalny obiekt.

		String r1 = "FESTIVAL-2026";
		String r2 = "FESTIVAL" + "-2026";
		String p = "FESTIVAL";
		String r3 = p + "-2026";
		String r4 = new String("FESTIVAL-2026").intern();

		System.out.println(r1 == r2); // True, działamy w compile i znajdujemy ten obiekt w StringPoolu
		System.out.println(r1 == r3); // False, konkatenacja dziala w runtime i tworzymy nowy obiekt
		System.out.println(r1 == r4); // False, użycie new String wymusza stworzenie nowego obiektu na stercie
	}

	public static void modifyStaff(CinemaStaff s) {
		s = CinemaStaff.builder()
				.name(s.getName())
				.skillLevel(s.getSkillLevel())
				.onDuty(true)
				.build();
	}
	}

