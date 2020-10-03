import java.io.*;
import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagerImpl implements MailAccountManager {
    File file = new File("C:/Users/123/Desktop/email2.csv");

    public AccountManagerImpl() {

    }

    @Override
    public void registerNewAccount(String email, String password, Person person)
            throws DuplicateAccountException {
        ArrayList<String[]> listOfMassives = new ArrayList<>();
        String strToWrite = "";

        try (FileReader fr = new FileReader(file)) {

            Scanner scan = new Scanner(fr);
            if (scan.hasNextLine()) {
                while (scan.hasNextLine()) {
                    String[] massive = scan.nextLine().split(";", 0);
                    listOfMassives.add(massive);
                    if (massive[0].equals(email)) {
                        throw new DuplicateAccountException("Такой аккаунт уже существует");
                    }
                }
                for (String[] element : listOfMassives) {
                    strToWrite += element[0] + ";" + element[1] + ";" + element[2]
                            + ";" + element[3] + "\n";
                }

            }
            strToWrite += email + ";" + password + ";" + person.getInitials() +
                    ";" + person.getBirthdayDate() + "\n";
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
        try (FileWriter fw = new FileWriter(file)) {
            fw.append(strToWrite);
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException {
        ArrayList<String[]> listOfMassives = new ArrayList<>();
        String[] removalElement = {};
        String strToWrite = "";
        boolean accountExists = false;

        try (FileReader fr = new FileReader(file)){

            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
            }
            for(String[] element : listOfMassives) {
                if (element[0].equals(email) && element[1].equals(password)) {
                    removalElement = element;
                    accountExists = true;
                }
            }
            if (!accountExists) {
                throw new WrongCredentialsException("Такого аккаунта не существует. ");
            }
            else{
                listOfMassives.remove(removalElement);
            }

            for (String[] element2 : listOfMassives) {
                strToWrite += element2[0] + ";" + element2[1] + ";" + element2[2]
                        + ";" + element2[3] + "\n";
            }
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }

        try(FileWriter fw = new FileWriter(file)){
            fw.append(strToWrite);
        }catch (IOException exc) {
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
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws WrongMethodTypeException,
            TooManyLoginAttemptsException {
        boolean result = false;
        ArrayList<String[]> listOfMassives = new ArrayList<>();
        AttemptCounter a = AttemptCounter.getInstance();

        try (FileReader fr = new FileReader(file)) {
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                String[] massive = scan.nextLine().split(";", 0);
                listOfMassives.add(massive);
            }

            for (String[] element : listOfMassives) {
                if (element[0].equals(email) && element[1].equals(password)) {
                    a.getCount();
                    return new Person(element[2], element[3]);
                }
                else{
                    a.getCount();
                }
            }
            throw new WrongCredentialsException("Аккаунта с таким логином или " +
                    "паролем не существует.");
        }
        catch (IOException | WrongCredentialsException exc) {
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
