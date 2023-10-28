import java.util.Timer;
import java.util.TimerTask;

public class TrafficLight {
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        timer.scheduleAtFixedRate(new TimerTask() {
            private int state = 0; // 0: Red, 1: Green, 2: Yellow

            @Override
            public void run() {
                switch (state) {
                    case 0:
                        System.out.println("Red Light (Stop)");
                        break;
                    case 1:
                        System.out.println("Green Light (Go)");
                        break;
                    case 2:
                        System.out.println("Yellow Light (Prepare to Stop)");
                        break;
                }

                state = (state + 1) % 3;
            }
        }, 0, 2000); // Initial delay: 0ms, Repeat every 20 seconds

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Traffic light cycle completed.");
            }
        }, 6000); // After 60 seconds, print cycle completed

        // Terminate the program after a while (for testing purposes)
        try {
            Thread.sleep(360000); // Run for 6 minutes (10 cycles)
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
