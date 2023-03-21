package es.opplus.application.services.counter;

import es.opplus.application.components.Counter;
import es.opplus.application.services.broadcaster.BroadcasterService;
import org.springframework.stereotype.Component;

@Component
public class RandomCounterService extends Thread {

    static boolean goNext = true;
    static int sleepPeriod = 500;

    @Override
    public void run() {

        int counterValue = 0;

        while (goNext) {
            BroadcasterService.broadcast(Counter.class, new CounterData(String.format("%d", counterValue++)));

            try {
                Thread.sleep(sleepPeriod);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void para() {
        goNext = false;
    }

    public void setDelay(int delay) {
        if (delay < 500)
            delay = 500;
        sleepPeriod = delay;
    }

    public int getDelay() {
        return sleepPeriod;
    }

}
