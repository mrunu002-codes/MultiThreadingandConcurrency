package Multithreading.Syncronisation;


class ABCPrinter {
    private int turn=0;

    public synchronized void printA() {
        for(int i=0;i<5;i++){
            while(turn!=0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                System.out.println("A");
                turn=1;
                notifyAll();

        }
    }
    public synchronized void printB(){
        for(int i=0;i<5;i++){
            while(turn!=1){
                try{
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            turn=2;
            notifyAll();
        }
    }
    public synchronized void printC(){
        for(int i=0;i<5;i++){
            while(turn!=2){
                try{
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            turn=0;
            notifyAll();
        }
    }
}
public class ABCPrinting {
    public static void main(String[] args) {
        ABCPrinter abcPrinter=new ABCPrinter();
        new Thread(abcPrinter::printA).start();
        new Thread(abcPrinter::printB).start();
        new Thread(abcPrinter::printC).start();
    }
}
