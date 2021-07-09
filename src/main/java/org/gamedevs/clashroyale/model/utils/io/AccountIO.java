package org.gamedevs.clashroyale.model.utils.io;

import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.player.Human;

import java.io.*;

/**
 * a class for reading and writing account info as object in to file
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * refactored structure by Pouya Mohammadi.
 * @version 1.1
 */
public class AccountIO {

    /**
     * Only instance of account io api
     */
    private static AccountIO instance = null;

    /**
     * Address of currecnt account in files,
     * this class will 'write over'/'read from' it.
     */
    private String accountAddress;
    /**
     * Password is needed to communicate with account!
     */
    private String password;

    /**
     * write (append) new object in file
     * @param fileName fileName
     * @param account account obj
     */
    public void singleObjectFileWriter(String fileName, Account account) {
        ObjectOutputStream objectOutputStream = null;
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            if (bufferedInputStream.read() != -1) {             //check if its empty or not
                objectOutputStream = new MyObjectOutputStream(fileOutputStream);
            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            objectOutputStream.writeObject(account);
        } catch (FileNotFoundException e) {
            System.err.println("can't find " + fileName);
        } catch (IOException e) {
            System.err.println("some thing went wrong while writing in " + fileName);
        } finally {
            try {
                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * read an object (Human type) from file
     *
     * @param fileName fileName
     * @return human
     */
    public Account singleObjectFileReader(String fileName) {
        Account account;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            account = (Account) objectInputStream.readObject();
            return account;
        } catch (FileNotFoundException e) {
            System.err.println("can't find " + fileName);
        } catch (IOException e) {
            System.err.println("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            System.err.println("can't convert obj that has been written in " + fileName + "to Human");
        }
        return null;
    }

    /**
     * search in a file for a human with specific username and pass
     *
     * @param username username
     * @param fileName fileName
     * @param pass     password
     * @return human
     */
    public Account searchInFile(String fileName, String username, String pass) {
        Account account = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            account = (Account) objectInputStream.readObject();
            if (account.getUsername().equals(username) && ((Account) account).getPassword().equals(pass))
                return account;
        } catch (FileNotFoundException e) {
            System.err.println("can't find " + fileName);
        } catch (IOException e) {
            System.err.println("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            System.err.println("can't convert obj that has been written in " + fileName + "to Human");
        }
        return null;
    }

    /**
     * search in a file for a human with specific username
     *
     * @param username username
     * @param fileName fileName
     * @return human
     */
    public static boolean searchInFileByUsername(String fileName, String username) {
        Human human = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            human = (Human) objectInputStream.readObject();
            if (((Human) human).getUsername().equals(username))
                return true;
        } catch (FileNotFoundException e) {
            System.err.println("can't find " + fileName);
        } catch (IOException e) {
            System.err.println("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            System.err.println("can't convert obj that has been written in " + fileName + "to Human");
        }

        return false;
    }


    /**
     * a class to handle appending a new object to a file which previously contains some object of same type
     */
    static class MyObjectOutputStream extends ObjectOutputStream {

        public MyObjectOutputStream(OutputStream o) throws IOException {
            super(o);
        }

        public void writeStreamHeader() {
        }
        // This overrides the method in the parent class, so that he does not write to the file header when calling writeObject()
    }

    // Setting save account path.
    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }
    // Setting password of saved account!
    public void setPassword(String password) {
        this.password = password;
    }

    public static AccountIO getFileIO() {
        if(instance == null)
            instance = new AccountIO();
        return instance;
    }

}
