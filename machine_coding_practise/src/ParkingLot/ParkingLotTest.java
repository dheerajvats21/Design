package ParkingLot;
import ParkingLot.entities.*;
import ParkingLot.strategy.ParkingSlotStrategy;
import ParkingLot.strategy.SimpleParkingSlotStrategy;

import java.util.*;
import java.io.*;

public class ParkingLotTest {
    public static void main(String[] args) throws Exception {
//        FastReader in = new FastReader();
//        PrintWriter out = new PrintWriter(System.out);

        // Write test code or calls here
        List<ParkingSlot> slots = new ArrayList<>();
        VehicleType[] values = VehicleType.values();
        for (int i = 0; i < 15; ++i) {
            slots.add(new ParkingSlot(i, values[i % values.length]));
        }

        Floor one = new Floor(1, slots.subList(0, 5));
        Floor two = new Floor(2, slots.subList(5, 10));
        Floor three = new Floor(3, slots.subList(10, 15));



        Gate oneGate = new Gate(1, Gate.GateType.ENTRY);
        Gate twoGate = new Gate(2, Gate.GateType.EXIT);

        ParkingSlotStrategy strategy = new SimpleParkingSlotStrategy();
        ParkingLot parkingLot = new ParkingLotImpl(Arrays.asList(one, two, three), strategy);


        Vehicle oneV = new Vehicle(1, VehicleType.BIKE);
        Vehicle twoV = new Vehicle(2, VehicleType.CAR);
        Vehicle threeV = new Vehicle(3, VehicleType.TRUCK);



        Set<ParkingSlot> bikesAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.BIKE));
        System.out.println(bikesAvailableSlots);
        Ticket t1 = parkingLot.park(oneV, oneGate);
        bikesAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.BIKE));
        System.out.println(bikesAvailableSlots);

        Set<ParkingSlot> carsAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.CAR));
        System.out.println(carsAvailableSlots);
        Ticket t2 = parkingLot.park(twoV, oneGate);
        carsAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.CAR));
        System.out.println(carsAvailableSlots);

        Set<ParkingSlot> trucksAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.TRUCK));
        System.out.println(trucksAvailableSlots);
        Ticket t3 = parkingLot.park(threeV, oneGate);
        trucksAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.TRUCK));
        System.out.println(trucksAvailableSlots);


        System.out.println("ticket 1 = " + t1);
        System.out.println("ticket 2 = " + t2);
        System.out.println("ticket 3 = " + t3);


        Receipt oneReceipt = parkingLot.unpark(t1.getId(), twoGate);
        bikesAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.BIKE));
        System.out.println(bikesAvailableSlots);


        Receipt twoReceipt =  parkingLot.unpark(t2.getId(), twoGate);
        carsAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.CAR));
        System.out.println(carsAvailableSlots);

        Receipt threeReceipt = parkingLot.unpark(t3.getId(), twoGate);
        trucksAvailableSlots = parkingLot.displayAvailableSlots(Arrays.asList(VehicleType.TRUCK));
        System.out.println(trucksAvailableSlots);

        System.out.println("Receipt 1 = " + oneReceipt);
        System.out.println("Receipt 2 = " + twoReceipt);
        System.out.println("Receipt 3 = " + threeReceipt);



//        out.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
