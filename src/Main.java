public class Main {
    public static void main(String[] args) {
        AccountManagerImpl manager = new AccountManagerImpl();

        Person person1 = new Person("ОАП", "25.03.2000");
        Person person2 = new Person("КАД", "27.03.1996");
        Person person3 = new Person("ЛОЛ", "25.05.1956");

        try {
        manager.registerNewAccount("ks@", "456", person1);
        manager.registerNewAccount("ks@", "456", person2);
        manager.registerNewAccount("ks@", "456", person3);
        }
        catch(DuplicateAccountException exc) {
            System.out.println(exc.getMessage());
        }

    }
}
