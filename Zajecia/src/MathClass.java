public class MathClass {
    //Zadanie dla chętnych:
    //PRACA DOMOWA PD-03: Biblioteka metod z menu
    //Utwórz klasę MathLibrary z metodami:
    //static long factorial(int n) — OBIE wersje: iteracyjna i rekurencyjna
    //static boolean isPrime(int n) — optymalizacja do sqrt(n)
    //static int[] sieveOfEratosthenes(int limit) — sito Eratostenesa
    //static int gcd(int a, int b) — algorytm Euklidesa (rekurencja)
    //static double power(double base, int exp) — szybkie potęgowanie
    //Interaktywne menu: użytkownik wybiera funkcję, podaje parametry, widzi wynik
    //Benchmark: porównaj czas factorial iteracyjny vs rekurencyjny dla n=20
    //Każda metoda musi mieć komentarz JavaDoc (@param, @return).

    /**
     * Oblicza silnię n! - iteracja
     * @param n liczba całkowita nieujemna
     * @return silnia n! jako long
     */
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    /**
     * Oblicza silnię n! - rekurencja
     * @param n liczba całkowita nieujemna
     * @return silnia n! jako long
     */
    public static long factorialRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    /**
     * Sprawdza czy liczba n jest liczbą pierwszą
     * @param n sprawdzana liczba całkowita
     * @return true jeśli liczba n jest pierwsza, false gdy nie jest
     */
     public static boolean isPrime(int n) {
         if (n < 2) return false;
         if (n == 2) return true;
         if (n % 2 == 0) return false;
         for (int i = 3; i * i <= n; i += 2) {
             if (n % i == 0) return false;
         }
         return true;
     }

    /**
     * Sito Eratostenesa - zwraca tablicę liczb pierwszych nie większych niż limit
     * @param limit górna granica (włącznie) limit >= 2
     * @return tablica int[] zawierająca liczby pierwsze <= limit
     */
     public static int[] sieveOfEratosthenes(int limit) {
         boolean[] isComposite = new boolean[limit + 1];
         for (int i = 2; i * i <= limit; i++) {
             if (!isComposite[i]) {
                 for (int j = i * i; j <= limit ; j++) {
                     isComposite[i] = true;
                 }
             }
         }
         //Zliczanie liczb pierwszych
         int count = 0;
         for (int i = 2; i <= limit ; i++) {
             if (!isComposite[i]) count++;
         }
         int[] primes = new int[count];
         int idx = 0;
         for (int i = 2; i <= limit; i++) {
             if (!isComposite[i]) primes[idx++] = i;
         }
         return primes;
     }

    /**
     * Obliczanie wspólnego dzielnika przy użyciu algorytmu Euklidesa (rekurencja)
     * @param a pierwsza liczba całkowita
     * @param b druga liczba całkowita
     * @return GCD
     */
     public static int gcd(int a, int b) {
         if (b == 0) {
             return a;
         }
         return gcd(b, a % b);
     }


    /**
     * Szybkie potęgowanie
     * @param base podstawa potęgi
     * @param exp wykładnik potęgi
     * @return base podniesione do potęgi exp
     */
     public static double power(double base, int exp) {
         if (exp == 0) return 1;
         if (exp % 2 == 0) {
             double half = power(base, exp / 2);
             return half * half;
         }
         return base * power(base, exp - 1);
     }
}
