package ElevatorSystem;

import ElevatorSystem.entities.MoverTypes;
import ElevatorSystem.entities.Request;

import java.util.*;
import java.io.*;

public class ElevatorSystemTest {
    public static void main(String[] args) throws Exception {
//        FastReader in = new FastReader();
//        PrintWriter out = new PrintWriter(System.out);

        // Write test code or calls here

        Elevator one = new Elevator(MoverTypes.SIMPLE, 1);
        Elevator two = new Elevator(MoverTypes.SIMPLE, 2);
        Elevator three = new Elevator(MoverTypes.SIMPLE, 3);

        ElevatorSystemInterface system = new ElevatorSystemInterface(Arrays.asList(one, two, three));
        system.externalRequest(new Request(null, 0, Request.RequestType.EXTERNAL));
        Thread.sleep(10000);
        system.internalRequest(new Request(1, 2, Request.RequestType.INTERNAL));
        Thread.sleep(10000);

        system.externalRequest(new Request(null, 0, Request.RequestType.EXTERNAL));
        system.externalRequest(new Request(null, 0, Request.RequestType.EXTERNAL));
        system.externalRequest(new Request(3, 3, Request.RequestType.INTERNAL));
        system.externalRequest(new Request(2, 6, Request.RequestType.INTERNAL));
        system.externalRequest(new Request(2, 9, Request.RequestType.INTERNAL));
        system.externalRequest(new Request(2, 0, Request.RequestType.INTERNAL));
        system.externalRequest(new Request(2, 2, Request.RequestType.INTERNAL));

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
