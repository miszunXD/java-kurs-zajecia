import java.util.Arrays;
import java.util.Scanner;

public class MathClassTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== BIBLIOTEKA MATEMATYCZNA ===");
            System.out.println("1. Silnia (iteracyjna)");
            System.out.println("2. Silnia (rekurencyjna)");
            System.out.println("3. Sprawdź czy liczba jest pierwsza");
            System.out.println("4. Sito Eratostenesa");
            System.out.println("5. NWD algorytmem Euklidesa");
            System.out.println("6. Szybkie potęgowanie");
            System.out.println("7. Benchmark: silnia iteracyjna vs rekurencyjna");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Podaj n (n - 20): ");
                    int n = sc.nextInt();
                    System.out.println("Wynik: " + MathClass.factorialIterative(n));
                    break;
                }
                case 2: {
                    System.out.print("Podaj n (n - 20): ");
                    int n = sc.nextInt();
                    System.out.println("Wynik " + MathClass.factorialRecursive(n));
                    break;
                }
                case 3: {
                    System.out.print("Podaj liczbę: ");
                    int n = sc.nextInt();
                    boolean prime = MathClass.isPrime(n);
                    System.out.println(n + (prime ? " jest liczbą pierwszą."
                                                    : " NIE jest liczbą pierwszą."));
                    break;
                }
                case 4: {
                    System.out.print("Podaj liczbę: ");
                    int limit = sc.nextInt();
                    int[] primes = MathClass.sieveOfEratosthenes(limit);
                    System.out.println("Liczby pierwsze do " + limit + ":\n" + Arrays.toString(primes));
                    System.out.println("Znaleziono: " + primes.length);
                    break;
                }
                case 5: {
                    System.out.print("Podaj a: ");
                    int a = sc.nextInt();
                    System.out.print("Podaj b: ");
                    int b = sc.nextInt();
                    System.out.println("NWD( " + a + " + " + b + ") = " + MathClass.gcd(a, b));
                    break;
                }
                case 6: {
                    System.out.print("Podaj podstawę: ");
                    double base = sc.nextDouble();
                    System.out.print("Podaj wykładnik: ");
                    int exp = sc.nextInt();
                    System.out.println(base + "^" + exp + " = " + MathClass.power(base, exp));
                    break;
                }
                case 7: {
                    runBenchmark();
                    break;
                }
                case 0: {
                    running = false;
                    System.out.println("Narty, z fartem wariacie");
                    break;
                }
                default:
                    System.out.println("Nieprawidłowy wybór! Wybierz opcje z pośród 0 - 7");
            }
        }
        sc.close();
    }

    private static void runBenchmark() {
        final int N = 20;
        final int ITERATIONS = 1_000_000;

        //Rozgrzewka JVM
        for (int i = 0; i < 1000; i++) {
            MathClass.factorialIterative(N);
            MathClass.factorialRecursive(N);
        }

        //Benchmark iteracyjny
        long startIter = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            MathClass.factorialIterative(N);
        }
        long timeIter = System.nanoTime() - startIter;

        //Benchmark rekurencyjny
        long startRec = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            MathClass.factorialRecursive(N);
        }
        long timeRec = System.nanoTime() - startRec;

        System.out.println("\n--- BENCHMARK: factorial(20) x 1 000 000 ---");
        System.out.printf("Iteracyjna:   %,d ms%n", timeIter / 1_000_000);
        System.out.printf("Rekurencyjna: %,d ms%n", timeRec / 1_000_000);
        System.out.println("Wynik (sprawdzenie): 20! = " + MathClass.factorialIterative(20));


        if (timeIter < timeRec) {
            System.out.println("Szybsza: iteracyjna");
        } else {
            System.out.println("Szybsza: rekurencyjna");
        }
    }
}
