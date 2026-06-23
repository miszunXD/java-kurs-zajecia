package live_coding_june.model;

import java.util.Objects;

public class Passport {
    private String passportNumber;
    private String holderName;

    public Passport(String passportNumber, String holderName) {
        this.passportNumber = passportNumber;
        this.holderName = holderName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(passportNumber, passport.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(passportNumber);
    }
}
