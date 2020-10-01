import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagerImpl implements MailAccountManager {

    public AccountManagerImpl(){

    }

    @Override
    public void registerNewAccount(String email, String password, Person person)
            throws DuplicateAccountException {
        File file = new File("C:/Users/123/Desktop/email2.csv");
        ArrayList<String[]> listOfMassives= new ArrayList<>();
        String strToWrite = "";

        try {
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            while(scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
                if(massive[0].equals(email)) {
                    throw new DuplicateAccountException();
                }
            }

            FileWriter fw = new FileWriter(file);

            for(String[] element : listOfMassives) {
                strToWrite += element[0] + ";" + element[1] + ";" + element[2]
                        + ";" + element[3] + "\n";
            }
            strToWrite += email + ";" + password + ";" + person.getInitials() +
                    ";" + person.getBirthdayDate() + "\n";

            fw.append(strToWrite);

            fr.close();
            fw.close();

        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
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
