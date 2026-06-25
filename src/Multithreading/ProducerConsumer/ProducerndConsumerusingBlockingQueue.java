package Multithreading.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Why is BlockingQueue preferred?
//
//Because it internally uses locks and condition variables to handle synchronization correctly.
class ProducerConsumerUsingBlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Thread producer= new Thread(
                () ->{
                    int value=1;
                    while(true){
                        try{
                            queue.put(value); //waits if queue is full- it blocks but offer will not wait
                            System.out.println("Produced : " + value);
                            value++;
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e){
                            Thread.currentThread().interrupt();

                        }
                    }

                }
        );
        Thread consumer=new Thread(
                () -> {
                    while(true){
                        try{
                            int value =queue.take();
                            System.out.println("Consumed : " + value);

                            Thread.sleep(1000);

                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        consumer.start();
    }
}
