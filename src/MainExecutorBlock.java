import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainExecutorBlock {

    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                .collect(Collectors.toList());

        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final List<Future<String>> futures = new ArrayList<>();

        numbers.forEach(n -> futures.add(threadPool.submit(new CallBack(n))));

        threadPool.shutdown();

        for (Future f : futures) {
            try {

                System.out.println(LocalDateTime.now() + "--" + f.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
