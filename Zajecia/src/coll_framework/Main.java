package coll_framework;

import java.util.HashSet;

public class Main {
    /*
    1. WybraŁbym ArrayListe. Złożoność dodawania elementów w ArrayLst jest taka sama jak w LinkedList 0(1),
    jednakże z powodu wyszukiwania losowsego loga po indeksie lepszym wyborem jest ArrayList, który wyszukuje
    przy złożoności 0(1) kiedy LinkedList robi to przechodząc po każdym elemencie 0(n).
     */

    /*
    2.Wypisze 1 z powodu porównywania jedynie po cenie. Jeżeli chcemy wskazać realny rozmiar TreeSetu należy dodać
    do Comparatora thenComparing(Product::name). W ten sposób mamy dwie metody porównywania obiektów w TreeSecie
    i nie są traktowane jako jeden z powodu takiej samej ceny.
     */

    public static void main(String[] args) {
        HashSet<Supplier> suppliers = new HashSet<>();
        suppliers.add(new Supplier("Tyskie"));
        suppliers.add(new Supplier("Audi"));
        suppliers.add(new Supplier("BMW"));

        System.out.println("Rozmiar HashSetu Suppliers: ");
        System.out.println(suppliers.size());


        OrderManager orderManager = new OrderManager();

        orderManager.assignPackageToZone("WARSZAWA", "P-1");
        orderManager.assignPackageToZone("WARSZAWA", "P-2");
        orderManager.assignPackageToZone("KRAKÓW", "P-3");

        orderManager.registerCurier(new Courier("Jan", 80));
        orderManager.registerCurier(new Courier("Anna", 20));
        orderManager.registerCurier(new Courier("Marek", 50));

        orderManager.processDispatch();
    }
}
