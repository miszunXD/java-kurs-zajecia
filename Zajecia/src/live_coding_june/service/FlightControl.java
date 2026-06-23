package live_coding_june.service;

import live_coding_june.model.Aircraft;

import java.util.*;

public class FlightControl {
    private final Map<String, List<String>> gateBoardingList = new HashMap<>();
    private final PriorityQueue<Aircraft> takeoffQueue;


    public FlightControl() {
        this.takeoffQueue = new PriorityQueue<>(Comparator.comparingInt(Aircraft::emergencyLevel)
                .reversed());
    }

    public void assignPassengerToGate(String gate, String passengerName) {
        gateBoardingList.computeIfAbsent(gate, key -> new ArrayList<>())
                .add(passengerName);
    }

    public void registerDeparture(Aircraft aircraft) {
        takeoffQueue.offer(aircraft);
    }

    public void processTakeoffs() {
        while (!takeoffQueue.isEmpty()) {
            Aircraft aircraft = takeoffQueue.poll();

            System.out.println("[WIEŻA] Zezwalam na start: [" + aircraft.flightNumber()
                    + "] (Priorytet: ["
            + aircraft.emergencyLevel() + "])");
        }
    }


}
