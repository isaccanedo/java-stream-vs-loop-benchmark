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

### O que esse código faz?

1 - Gera 10 milhões de números aleatórios.

2 - Calcula o quadrado de cada número:

- Usando for tradicional.
- Usando stream().
- Usando parallelStream().

Mede o tempo gasto em cada abordagem.

👉 Resultados esperados (podem variar dependendo do hardware):

O loop tradicional costuma ser o mais rápido em operações muito simples (menos overhead).
O stream sequencial pode ser um pouco mais lento, devido à abstração.
O parallelStream() geralmente vence em grandes volumes, porque distribui o trabalho entre múltiplos núcleos.
