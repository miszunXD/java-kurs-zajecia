import java.util.Scanner;

public class CalculatorBmi {

    enum Kategoria {
        NIEDOWAGA,
        NORMA,
        NADWAGA,
        OTYLOSC
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj swoją wagę [kg]: ");
        double weight = sc.nextDouble();

        System.out.println("Podaj swój wzrost [cm]: ");
        double height = sc.nextDouble();

        if (height < 30 || height > 240) {
            System.out.println("Wzrost nieprawidłowy!");
        }

        double heightMetres = height / 100.0;
        double bmi = weight / (heightMetres * heightMetres);

        System.out.printf("BMI: %.2f\n", bmi);

        if (bmi < 18.5) {
            System.out.println(Kategoria.NIEDOWAGA);
        } else if (bmi < 24.9) {
            System.out.println(Kategoria.NORMA);
        } else if (bmi < 29.9) {
            System.out.println(Kategoria.NADWAGA);
        } else
            System.out.println(Kategoria.OTYLOSC);

        sc.close();
    }
}
