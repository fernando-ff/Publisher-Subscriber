package imd.ufrn;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer<T> {
    private Queue<T> data;
    private final Semaphore semaphoreP;
    private final Semaphore semaphoreC;

    public Buffer(Queue<T> buffer) {
        this.data = buffer;
        this.semaphoreP =  new Semaphore(10, true);
        this.semaphoreC =  new Semaphore(0, true);

    }

    public void add(T i) {
        try {
            semaphoreP.acquire();
            semaphoreC.release();
            synchronized (this) {
                data.add(i);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public T remove() {
        try {
            semaphoreC.acquire();
            semaphoreP.release();
            synchronized (this) {
                return data.remove();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
