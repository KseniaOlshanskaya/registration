public class AccountManagerImpl implements MailAccountManager {

    public AccountManagerImpl(){

    }

    @Override
    public void registerNewAccount(String email, String password, Person person)
            throws DuplicateAccountException {
        
    }

    @Override
    public void removeAccount(String email, String password) {

    }

    @Override
    public boolean hasAccount(String email) {
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException {
        return null;
    }

    @Override
    public int numOfAccounts() {
        return 0;
    }

}
