import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sala[][] = new int[5][5];

        sala[0][1] = 1;
        sala[1][1] = 1;
        sala[2][0] = 1;
        sala[3][1] = 1;
        sala[4][3] = 1;


        int wybor;

        do {
            System.out.println("--- SYSTEM REZERWACJI KINA JavaMultiplex ---");
            System.out.println("1. Pokaż salę");
            System.out.println("2. Rezerwuj");
            System.out.println("3. Cena grupowa");
            System.out.println("0. Wyjdź");

            wybor = sc.nextInt();

            switch (wybor) {
                case 1:
                    wyswietlSale(sala);
                    break;

                case 2:
                    rezerwujMiejsce(sala, sc);
                    break;

                case 3:
                    System.out.print("Ile biletów chcesz kupić? ");
                    int iloscBiletow = sc.nextInt();
                    System.out.print("Podaj cenę bazową biletu: ");
                    double cenaBazowaBiletu = sc.nextDouble();
                    double cena = obliczCeneGrupowa(iloscBiletow, cenaBazowaBiletu);

                    System.out.println("Obliczanie ceny metodą rekurencyjną...");
                    System.out.printf("Łączna cena za 3 bilety po rabatach: %.2f zł%n", cena);

                case 0:
                    System.out.println("Dziękujemy za skorzystanie z systemu. Do widzenia!");
                    break;

                default:
                    System.out.println("Błąd, niepoprawna opcja!");
            }
        } while (wybor != 0);

        sc.close();

    }

    public static double obliczCeneGrupowa(int iloscBiletow, double cenaBazowaBiletu) {
        if (iloscBiletow <= 1) {
            return cenaBazowaBiletu;
        }
        return cenaBazowaBiletu + obliczCeneGrupowa
                (iloscBiletow - 1, cenaBazowaBiletu * 0.95);
    }

    private static void wyswietlSale(int [][] sala) {
        System.out.println("MAPA SALI");

        for (int i = 0; i < sala[0].length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 0; i < sala.length; i++) {
            System.out.print(i +  " : ");

            for (int j = 0; j < sala[i].length; j++) {
                if (sala[i][j] == 0) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void rezerwujMiejsce(int [][] sala, Scanner sc) {
        System.out.println("Podaj numer rzedu: 1 - 5: ");
        int rzad = sc.nextInt();
        System.out.println("Podaj numer miejsca: 1 - 5: ");
        int miejsce = sc.nextInt();

        if (rzad < 0 || rzad >= sala.length || miejsce < 0 || miejsce >= sala[rzad].length) {
            System.out.println("Nie ma takiego miejsca!");
            return;
        }

        if (sala[rzad][miejsce] == 1) {
            System.out.println("Miejsce jest zajęte!");
        } else {
            sala[rzad][miejsce] = 1;
            System.out.println("Rezerwacja zakończona powodzeniem!");
        }
    }
}

