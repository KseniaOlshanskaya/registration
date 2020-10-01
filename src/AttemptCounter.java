public class AttemptCounter {
    private static AttemptCounter instance;
    public static int counter = 0;

    public static synchronized AttemptCounter getInstance() throws TooManyLoginAttemptsException {
        if(instance == null) {
            counter = 1;
            instance = new AttemptCounter();
        }
        else {
            counter++;
        }
        instance.getCount();
        return instance;
    }

    public void getCount() throws TooManyLoginAttemptsException {
        if(counter >= 5) {
            throw new TooManyLoginAttemptsException("Слишком много попыток ввода. ");
        }
    }
}
