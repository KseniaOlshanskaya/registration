public class Main {

    public static void main(String[] args) {
        AccountManagerImpl manager = new AccountManagerImpl();

        Person person1 = new Person("HGF", "25.03.2000");
        Person person2 = new Person("LKJ", "27.03.1996");
        Person person3 = new Person("SKM", "25.05.1956");
        Person person4 = new Person("LOL", "24.03.1998");

        try {
            manager.registerNewAccount("gtffdh@", "456", person1);
            manager.registerNewAccount("JFRIJFR@", "123", person2);
            manager.registerNewAccount("lJFJFK@", "789", person3);
            manager.registerNewAccount("pJDJD4@", "789", person4);
        } catch (DuplicateAccountException exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println("Есть ли такой аккаунт: " + manager.hasAccount("david@"));

        try {
            System.out.println(manager.getPerson("huhuh@", "446"));
            System.out.println(manager.getPerson("huhuh@", "446"));
            System.out.println(manager.getPerson("huhuh@", "446"));
            System.out.println(manager.getPerson("huhuh@", "446"));
            System.out.println(manager.getPerson("huhuh@", "446"));
            System.out.println(manager.getPerson("huhuh@", "446"));

        } catch (TooManyLoginAttemptsException exc) {
            System.out.println(exc.getMessage());

        }

        System.out.println(manager.numOfAccounts());

        try {
           manager.removeAccount("gtffdh@", "456");
        }
        catch(WrongCredentialsException exc) {
            System.out.println(exc.getMessage());
        }

        try {
            manager.removeAccount("lJFJFK@", "789");
        }
        catch(WrongCredentialsException exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println(manager.numOfAccounts());
    }
}
