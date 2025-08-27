## java-stream-vs-loop-benchmark
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

### Vamos aumentar a complexidade da operação, para que o paralelismo do parallelStream() realmente faça diferença.

```
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
```

## Mudanças principais:

- Troquei Integer por Double para permitir potências grandes.
- A operação agora é Math.pow(n, 10), bem mais custosa que multiplicar por si mesmo.
- Mantive o volume grande de dados (10 milhões), assim o paralelismo terá efeito visível.

