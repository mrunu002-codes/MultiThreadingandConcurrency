package Multithreading.Syncronisation;

class Printer{
    int num=1;
    synchronized void printEven() throws InterruptedException {
        while(num<=10){
            while(num%2==0){
                wait();
            }
            System.out.println(num);
            num++;
            notify();
        }
    }
    synchronized void printOdd() throws InterruptedException{
        while(num<=10){
            while(num%2==1){
                wait();
            }
            System.out.println(num);
            num++;
            notify();
        }
    }
}
public class OddEvenPrinter {
    public static void main(String[] args) {

        Printer printer=new Printer();
        Thread t1 =new Thread(
                () -> {
                    try{
                        printer.printEven();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
        );
        Thread t2= new Thread(
                () -> {
                    try{
                        printer.printOdd();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
        );
        t1.start();
        t2.start();
    }
}
