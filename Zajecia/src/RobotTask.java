public class RobotTask {
    private static final int MAX_ENERGY = 100;
        // 1. Przeciążanie - Metoda do ładowania o konkretną liczbę jednostek
        public static int charge(int currentEnergy, int energyToAdd) {
            int result = currentEnergy + energyToAdd;

            return Math.min(result, MAX_ENERGY);
        }

        // 2. Przeciążanie - Metoda do ładowania "do pełna" (użyj boolean fullCharge)
        public static int charge(int currentEnergy, boolean fullCharge) {
            if (fullCharge) {
                return MAX_ENERGY;
            }
            return currentEnergy;
        }

        // 3. Rekurencja - Obliczanie zużycia energii na powrót (Suma liczb od n do 0)
        // Przykład: dla n=3, wynik to 3+2+1+0 = 6
        public static int calculateReturnPath(int distance) {
            if (distance == 0) {
                return 0;
            }

            return distance + calculateReturnPath(distance - 1);
        }

        // 4. SRP - Metoda procesowa (sklejająca)
        public static void performDailyRoutine() {
            int energy = 20;
            System.out.println("Startowa energia: " + energy);

            // Wywołaj charge...
            energy = charge(energy, 30);
            System.out.println("Energia po ładowaniu: " + energy);

            energy = charge(energy, true);
            System.out.println("Energia po ładowaniu do pełna: " + energy);

            // Wywołaj calculateReturnPath...
            int returnCost = calculateReturnPath(10);
            System.out.println("Koszt powrotu robota: " + returnCost);
            // Wypisz wyniki
        }

        public static void main(String[] args) {
            performDailyRoutine();
        }
    }