package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

	public static void main(String[] args) {
		
		// setting the threads
		
        Thread displayThread = new Thread(() -> {
            while (true) {
            	getCurrentTime();
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        displayThread.setPriority(Thread.MAX_PRIORITY);
		
        
        Thread updateThread = new Thread(() -> {
            while (true) {
                updateCurrentTime();
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.setPriority(Thread.MIN_PRIORITY);

        // Start the threads
        displayThread.start();
        updateThread.start();

	}
	
	// it is supposed we have to create a method to display the time to the console separately of the updating method
	// in that case we will create two separated methods synchronized
	
	private static void getCurrentTime() {
		String date = dateFormat.format(updateCurrentTime());
		System.out.println(date);
	}
	
	private static Date updateCurrentTime() {
		Date currentTime = new Date();
		return currentTime;
	}
}
