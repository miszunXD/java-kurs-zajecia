package smart_warehouse_logistic_system;

public class Main {
      static void main(String[] args) {
        Warehouse<Electronics> electronics = new Warehouse<>();

         Electronics laptop = new Electronics("Laptop", 2500.00, 24);
         Electronics laptopCopy = new Electronics(laptop, 36);

         electronics.addProduct(laptop);
         electronics.addProduct(laptopCopy);

         electronics.showInventory();

         for (Electronics e : electronics.getProducts()) {
             if (e instanceof Shipable shipable) {
                 e.ship("Kolejowa 14, 95-050 Konstantynów Łódzki");
             }
         }
     }
}
