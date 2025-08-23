# java-stream-vs-loop-benchmark
🚀 Exemplo em Java 8+ que compara a performance de um loop tradicional, de um Stream sequencial e de um parallelStream() ao processar milhões de registros.

```
import java.util.*;
import java.util.stream.*;
import java.time.*;

public class StreamVsLoopTest {
    public static void main(String[] args) {
        int numElements = 10_000_000;
        List<Integer> data = new Random()
                .ints(numElements, 1, 100)
                .boxed()
                .collect(Collectors.toList());

        // --- Loop tradicional ---
        Instant start = Instant.now();
        List<Integer> squaredLoop = new ArrayList<>(data.size());
        for (Integer n : data) {
            squaredLoop.add(n * n);
        }
        Instant end = Instant.now();
        System.out.println("Loop tradicional: " +
                Duration.between(start, end).toMillis() + " ms");

        // --- Stream sequencial ---
        start = Instant.now();
        List<Integer> squaredStream = data.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        end = Instant.now();
        System.out.println("Stream sequencial: " +
                Duration.between(start, end).toMillis() + " ms");

        // --- Parallel Stream ---
        start = Instant.now();
        List<Integer> squaredParallel = data.parallelStream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        end = Instant.now();
        System.out.println("Parallel Stream: " +
                Duration.between(start, end).toMillis() + " ms");
    }
}


```
