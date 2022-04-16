package imd.ufrn;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        List<Subscribe> consumerList = new ArrayList<>();
        Buffer<Integer> buffer = new Buffer<>(new ArrayDeque<>());
        Publisher<Integer> p1;
        Publisher<Integer> p2;


        for (int i=0; i < 9; i++) {
            consumerList.add(new Subscribe(buffer));
        }
        p1 =  new Publisher(buffer, consumerList);

        for (int i = 0; i < 1; i++) {
            p1.addNext(i);
        }

        p1.notifyConsumers();

        long sum = 0;
        int j;
        for( j = 0; j < 2000000; j++) {
            p1.addNext(j);
            sum += Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        }

        try {
            p1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sum/j);

    }
}
