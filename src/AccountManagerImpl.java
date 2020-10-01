import java.io.*;
import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagerImpl implements MailAccountManager {
    File file = new File("C:/Users/123/Desktop/email2.csv");

    public AccountManagerImpl(){

    }

    @Override
    public void registerNewAccount(String email, String password, Person person)
            throws DuplicateAccountException {
        ArrayList<String[]> listOfMassives= new ArrayList<>();
        String strToWrite = "";

        try(FileReader fr = new FileReader(this.file);
            FileWriter fw = new FileWriter(this.file)) {

            Scanner scan = new Scanner(fr);
            while(scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
                if(massive[0].equals(email)) {
                    throw new DuplicateAccountException("Такой аккаунт уже существует");
                }
            }
            for(String[] element : listOfMassives) {
                strToWrite += element[0] + ";" + element[1] + ";" + element[2]
                        + ";" + element[3] + "\n";
            }
            strToWrite += email + ";" + password + ";" + person.getInitials() +
                    ";" + person.getBirthdayDate() + "\n";

            fw.append(strToWrite);
        }
        catch(IOException exc) {
            exc.printStackTrace(System.out);
        }
    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException {
        ArrayList<String[]> listOfMassives= new ArrayList<>();
        String strToWrite = "";
        boolean accountExists = false;

        try(FileReader fr = new FileReader(this.file);
            FileWriter fw = new FileWriter(this.file)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
                if (massive[0].equals(email) && massive[1].equals(password)) {
                    listOfMassives.remove(massive);
                    accountExists = true;
                }
            }
            if(!accountExists) {
                throw new WrongCredentialsException("Такого аккаунта не существует. ");
            }
            for(String[] element : listOfMassives) {
                strToWrite += element[0] + ";" + element[1] + ";" + element[2]
                        + ";" + element[3] + "\n";
            }

            fw.append(strToWrite);
        }
        catch(IOException exc) {
            exc.printStackTrace(System.out);
        }
    }

    @Override
    public boolean hasAccount(String email) {
        try (FileReader fr = new FileReader(this.file)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                if (massive[0].equals(email)) {
                    return true;
                }
            }
        }
        catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException {
        try (FileReader fr = new FileReader(this.file)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                if (massive[0].equals(email) && massive[1].equals(password)) {
                    return new Person(massive[2], massive[3]);
                }
                else {
                    AttemptCounter.getInstance();
                    if (AttemptCounter.counter == 5) {
                        throw new TooManyLoginAttemptsException("Слишком много попыток входа");
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public int numOfAccounts() {
        ArrayList<String[]> listOfMassives= new ArrayList<>();
        try(FileReader fr = new FileReader(this.file)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
            }
        }
        catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
        return listOfMassives.size();
    }
}
