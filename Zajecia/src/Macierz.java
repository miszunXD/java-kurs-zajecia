import java.util.Scanner;

public class Macierz {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Utworzenie macierzy
        // O rozmiarze 3x3

        int[][] macierz = new int[3][3];
        
        //Wczytanie liczb od użytkownika
        //Wczytanie kolejno wiersz 0, 1, 2 ->

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Podaj 9 liczb do macierzy: ");
                macierz[i][j] = scanner.nextInt();
            }
        }

        //Wysweitlanie macierzy w czytelnym formacie
        //Wydzielone do osobnej metody, wyswietlanie uzyte ponownie w dalszej czesci kodu
        System.out.println("Macierz: ");
        wyswietlanieMacierzy(macierz);

        //Oblicz i wyświetl transpozycje macierzy
        //Zamiana wierzy na koluemny
        int rows = macierz.length;
        int cols = macierz[0].length;
        int[][] transposed = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                transposed[c][r] = macierz[r][c];
            }
        }
        System.out.println("Macierz po transpozycji: ");
        wyswietlanieMacierzy(transposed);

        //Obróć macierz o 90 stopni w prawo
        //Pierwszy wiersz zstaje sie ostatnia kolumną

        int[][] macierz90 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macierz90[j][2-i] = macierz[i][j];
            }
        }
        System.out.println("Macierz odwrócona o 90 stopni: ");
        wyswietlanieMacierzy(macierz90);

        //Znajdź i wyświetl sumę każdego wiersza i każdej kolumny
        //Wiersze - suma od [i][j]
        for (int i = 0; i < 3; i++) {
            int suma = 0;
            for (int j = 0; j < 3; j++) {
                suma += macierz[i][j];
            }
            System.out.println("Suma wiersza " + i + ": " + suma);
        }

        //Kolumny - suma od [j][i]
        for (int i = 0; i < 3; i++) {
            int suma = 0;
            for (int j = 0; j < 3; j++) {
                suma += macierz[j][i];
            }
            System.out.println("Suma kolumny " + i + ": " + suma);
        }

        //Sprawdz czy macierz jest symetryczna (a[i][j] == a[j][i])
        //Uzycie flagi, if else poza petla, inaczej dla kazdego iterowania sprawdza warunek

        boolean symetryczna = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (macierz[i][j] != macierz[j][i]) {
                    symetryczna = false;
                }
            }
        }

        if (symetryczna) {
            System.out.println("Macierz jest symetryczna");
        } else {
            System.out.println("Macierz jest niesymetryczna");
        }

    }

    private static void wyswietlanieMacierzy(int[][] macierz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(macierz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
