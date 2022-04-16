package imd.ufrn;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Publisher<T> extends Thread{

    private List<Subscribe> subscribes;
    private Buffer<T> buffer;


    public Publisher(Buffer<T> buffer, List<Subscribe> consumers) {
        this.buffer = buffer;
        this.subscribes = consumers;
    }

    public void addConsumer(Subscribe c) {
        System.out.println("[LOG]  Publiher.addConsumer #" + (subscribes.size()+1));
        subscribes.add(c);
    }

    public void removeConsumer(Subscribe c) {
        buffer.remove();
    }

    public void addNext(T i) {
        buffer.add(i);
        System.out.println(String.format("[LOG] Publisher.addNext(%d)", i));
    }

    public void notifyConsumers() {
        System.out.println("[LOG]  Publisher.notifyConsumers");
        this.subscribes.forEach(s -> {
            s.start();
        });

    }


}
