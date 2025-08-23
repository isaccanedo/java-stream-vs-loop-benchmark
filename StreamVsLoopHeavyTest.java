import java.util.*;
import java.util.stream.*;
import java.time.*;

public class StreamVsLoopHeavyTest {
    public static void main(String[] args) {
        int numElements = 10_000_000;
        List<Double> data = new Random()
                .doubles(numElements, 1, 100)
                .boxed()
                .collect(Collectors.toList());

        // --- Loop tradicional com operação pesada ---
        Instant start = Instant.now();
        List<Double> resultLoop = new ArrayList<>(data.size());
        for (Double n : data) {
            resultLoop.add(Math.pow(n, 10)); // operação pesada
        }
        Instant end = Instant.now();
        System.out.println("Loop tradicional: " +
                Duration.between(start, end).toMillis() + " ms");

        // --- Stream sequencial ---
        start = Instant.now();
        List<Double> resultStream = data.stream()
                .map(n -> Math.pow(n, 10))
                .collect(Collectors.toList());
        end = Instant.now();
        System.out.println("Stream sequencial: " +
                Duration.between(start, end).toMillis() + " ms");

        // --- Parallel Stream ---
        start = Instant.now();
        List<Double> resultParallel = data.parallelStream()
                .map(n -> Math.pow(n, 10))
                .collect(Collectors.toList());
        end = Instant.now();
        System.out.println("Parallel Stream: " +
                Duration.between(start, end).toMillis() + " ms");
    }
}
