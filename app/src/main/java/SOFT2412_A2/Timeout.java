package SOFT2412_A2;

import java.util.Scanner;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Timeout {
    
    public static String inputTimeout(Scanner scan) {
        try {
            FutureTask<String> task = new FutureTask<>(() -> {
                return scan.nextLine(); 
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            String userInput = task.get(120, TimeUnit.SECONDS);
            System.out.println("");
            if (userInput.equals("Cancel")) {
                System.out.println("\nOrder has been cancelled\n");
                return null;
            }

            return userInput;
    
        } catch (TimeoutException interruptedException) {
            
        } catch (Exception e) {}

        System.out.println("\nError: Timeout transaction is cancelled \n");
        return null;
    }     
}
