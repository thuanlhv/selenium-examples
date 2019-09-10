package automation.example.utils;

public class CommonUtils {
    public static void sleep(int timeInSecond) {
        try {
            logInfo(String.format("Sleeping in %s", timeInSecond));
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
}
