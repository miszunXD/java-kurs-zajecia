public class Products {

    public static void wyswietlOdKonca(String[] products, int index) {
        if (index < 0) {
            return;
        }
        System.out.println(products[index]);
        wyswietlOdKonca(products, index - 1);
    }

    public static void main(String[] args) {
        String[] products = new String[5];

        products[0] = "Goophone 17 Pro Max";
        products[1] = "Adacas FoamRunner";
        products[2] = "Jeezy Slides";
        products[3] = "Mokebe S26 Ultra";
        products[4] = "Kompendium BOTA";

        wyswietlOdKonca(products, 3);
    }
}
