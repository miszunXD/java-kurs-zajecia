package audyt_kodu;


import java.util.Objects;

/*
Brak nadpisanego hashCode.
Domyślny hashcode z Object liczy hash na podstawie adresu pamięci (referencji)
Dwa różne obiekty z tymi samymi danymi będą miały różny hashcode i trafią do różnych bucketów.
Hashset nie wywoła equals, gdyż najpierw porównuje hashcode - ten się różni bez nadpisania.
 */
public class CustomerCard {
    private final String cardId;
    private final String ownerName;

    public CustomerCard(String cardId, String ownerName) {
        this.cardId = cardId;
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCard)) return false;
        CustomerCard other = (CustomerCard) o;
        return this.cardId.equals(other.cardId) && this.ownerName.equals(other.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, ownerName);
    }
}
