package imd.ufrn;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        List<Subscribe> consumerList = new ArrayList<>();
        Buffer<Integer> buffer = new Buffer<>(new ArrayDeque<>());
        Publisher<Integer> p1;


        for (int i=0; i < 5; i++) {
            consumerList.add(new Subscribe(buffer));
        }
        p1 =  new Publisher(buffer, consumerList);

        for (int i = 0; i < 1; i++) {
            p1.addNext(i);
        }

        p1.notifySubscribers();

            for(int i = 0; ; i++) {
                p1.addNext(i);
            }

    }
}
