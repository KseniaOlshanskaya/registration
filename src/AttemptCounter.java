import java.security.Signature;

public class AttemptCounter {
    private static AttemptCounter instance;
    public static int counter = 0;

    public static synchronized AttemptCounter getInstance() {
        if(instance == null) {
            counter = 1;
            instance = new AttemptCounter();
        }
        else {
            counter++;
        }
        return instance;
    }
}
