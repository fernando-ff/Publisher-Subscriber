package imd.ufrn;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class Subscribe<T> extends Thread{
    private Buffer<T> buffer;
    public Subscribe(Buffer<T> buffer) {
        this.buffer = buffer;
    }

    public void onNext() {
        System.out.println("[LOG] Consumer.onNext(" + buffer.remove() + ")");
    }

    @Override
    public void run() {
        while(true) {
            this.onNext();
        }
    }
}
