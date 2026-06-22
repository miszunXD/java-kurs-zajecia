package audyt_kodu;

import java.util.Objects;

// TODO: Hashcode generowany tylko z pola branchId.
/*
Mamy tu sytuacje gdzie metoda equals porownuje obiekt w oparciu o dwa pola city i branchId,
ale hashCode jest generowany tylko na podstawie pola branchId.
Przykladowo obiekty o tym samym branchId ale roznych miastach beda mialy ten hashCode pomimo tego,
ze equals zwroci nam false. Obiekty trafiaja do tego samego bucketu powodujac kolizje,
mimo że obiekty sa w teorii rózne.
 */
class PartnerBranch {
    private final String branchId;
    private final String city;

    public PartnerBranch(String branchId, String city) {
        this.branchId = branchId;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartnerBranch)) return false;
        PartnerBranch other = (PartnerBranch) o;
        // Sprawdzamy oba pola: branchId oraz city
        return this.branchId.equals(other.branchId) && this.city.equals(other.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, city);
    }
}

