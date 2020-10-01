public class AttemptCounter {
    private static AttemptCounter instance;
    public static int counter = 0;

    public static synchronized AttemptCounter getInstance() throws TooManyLoginAttemptsException {
        if(instance == null) {
            instance = new AttemptCounter();
        }
        return instance;
    }

    public void getCount() throws TooManyLoginAttemptsException {
        if(counter == 5) {
            counter = 0;
            throw new TooManyLoginAttemptsException("Слишком много попыток ввода. ");
        }
        counter++;
    }
}
