package coll_framework;

import java.util.*;

public class OrderManager {
    private final Map<String, List<String>> zonePackages = new HashMap<>();

    private final PriorityQueue<Courier> dispatchQueue;

    public void assignPackageToZone(String zone, String packageCode) {
        zonePackages.computeIfAbsent(zone, key -> new ArrayList<>())
                .add(packageCode);
    }

    public OrderManager() {
        this.dispatchQueue = new PriorityQueue<>(Comparator.comparingInt(Courier::loadPercentage));
    }

    public void registerCurier(Courier courier) {
        dispatchQueue.add(courier);
    }

    public void processDispatch() {
        while (!dispatchQueue.isEmpty()) {
            Courier courier = dispatchQueue.poll();


            System.out.println("[WYSYŁKA] Ładuję auto kuriera: " +
                    courier.name() + " (Zapełnienie: " + courier.loadPercentage() + "%)");
        }
    }
}
