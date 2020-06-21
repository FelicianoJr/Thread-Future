import java.util.concurrent.Callable;

public class CallBack implements Callable<String> {

    private final Integer number;

    public CallBack(Integer number) {
        this.number = number;
    }

    @Override
    public String call() throws Exception {
        if (this.number % 2 == 0) {
            Thread.sleep(5000);
        } else {
            Thread.sleep(1000);
        }
        return Thread.currentThread().getName() + "::" + this.number;
    }
}
