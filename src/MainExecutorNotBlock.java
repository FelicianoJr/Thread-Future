import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainExecutorNotBlock {

    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                .collect(Collectors.toList());

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(threadPool);

        numbers.forEach(n -> executorCompletionService.submit(new CallBack(n)));

        threadPool.shutdown();

        while (!threadPool.isTerminated()) {
            final Future<String> future;
            try {
                future = executorCompletionService.take();
                System.out.println(LocalDateTime.now() + "--" + future.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
