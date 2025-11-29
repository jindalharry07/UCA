package ExternalSort;

import java.io.*;
import java.util.*;

public class GenerateTestData {
    public static void main(String[] args) throws Exception {

        Random r = new Random();

        // --- Create random input file ---
        File inputFile = new File("./input.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            for (int i = 0; i < 100; i++) {
                // random string length between 1 and 20
                int count = 1 + r.nextInt(20);

                // random UUID fragment
                String d = UUID.randomUUID().toString().substring(0, count);

                bw.write(d);
                bw.newLine();
            }
        }

        // --- Run your ExternalSort ---
        sort f = new sort();
        f.sort(new File("./input.txt"), new File("./output.txt"));

        // --- Read input into a List for verification ---
        List<String> expected = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String s = br.readLine();
            while (s != null) {
                expected.add(s);
                s = br.readLine();
            }
        }

        // --- Sort expected output in-memory ---
        Collections.sort(expected);

        // --- Verify output file is sorted the same ---
        List<String> actual = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./output.txt"))) {
            String s = br.readLine();
            while (s != null) {
                actual.add(s);
                s = br.readLine();
            }
        }

        // --- Compare ---
        boolean ok = expected.equals(actual);

        if (ok) {
            System.out.println("SUCCESS: External sort matches in-memory sort.");
        } else {
            System.out.println("ERROR: Output does NOT match expected sort.");
        }
    }
}
