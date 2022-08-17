package bai1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler  = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Parse (), 0, 2, TimeUnit.SECONDS);

    }
}
