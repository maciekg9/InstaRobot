import javax.swing.*;
import java.util.Date;

public class MultiThreading {

    public static void likesServiceThread() {
        Thread queryThread = new Thread() {
            public  void run() {
                InstaService.likeService();
            }
        };
        queryThread.start();
    }

    public static void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Time now is " + (new Date()));
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
