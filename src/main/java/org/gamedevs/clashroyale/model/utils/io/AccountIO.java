package org.gamedevs.clashroyale.model.utils.io;

import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * a class for reading and writing account info as object in to file
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * refactored structure by Pouya Mohammadi.
 * @version 1.1
 */
public class AccountIO {

    /**
     * Path of directory of config file.
     */
    private final String DIR = "src/save/";

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
        File file = new File(DIR + fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            if (bufferedInputStream.read() != -1) {             //check if its empty or not
                objectOutputStream = new MyObjectOutputStream(fileOutputStream);
            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            objectOutputStream.writeObject(account);
        } catch (FileNotFoundException e) {
            Console.getConsole().printTracingMessage("can't find " + fileName);
        } catch (IOException e) {
            Console.getConsole().printTracingMessage("some thing went wrong while writing in " + fileName);
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
     * read an object (Account type) from file
     *
     * @param fileName fileName
     * @return account
     */
    public Account singleObjectFileReader(String fileName) {
        Account account;
        try (FileInputStream fileInputStream = new FileInputStream(DIR + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            account = (Account) objectInputStream.readObject();
            return account;
        } catch (FileNotFoundException e) {
            Console.getConsole().printTracingMessage("can't find " + fileName);
        } catch (IOException e) {
            Console.getConsole().printTracingMessage("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            Console.getConsole().printTracingMessage("can't convert obj that has been written in " + fileName + "to Account");
        }
        return null;
    }

    /**
     * search in a file for a account with specific username and pass
     *
     * @param username username
     * @param fileName fileName
     * @param pass     password
     * @return account
     */
    public Account searchInFile(String fileName, String username, String pass) {
        File file = new File(DIR + fileName);
        if(file.length() == 0)
            return null;

        Account account = null;
        try (FileInputStream fileInputStream = new FileInputStream(DIR + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            account = (Account) objectInputStream.readObject();
            if (account.getUsername().equals(username) && account.getPassword().equals(pass))
                return account;
        } catch (FileNotFoundException e) {
            Console.getConsole().printTracingMessage("can't find " + fileName);
        } catch (IOException e) {
            Console.getConsole().printTracingMessage("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            Console.getConsole().printTracingMessage("can't convert obj that has been written in " + fileName + "to Account");
        }
        return null;
    }

    /**
     * search in a file for a account with specific username
     *
     * @param username username
     * @param fileName fileName
     * @return account
     */
    public boolean searchInFileByUsername(String fileName, String username) {
        Account account = null;
        File file = new File(DIR + fileName);
        if(file.length() == 0)
            return false;

        try (FileInputStream fileInputStream = new FileInputStream(DIR + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            account = (Account) objectInputStream.readObject();
            if (account.getUsername().equals(username))
                return true;
        } catch (FileNotFoundException e) {
            Console.getConsole().printTracingMessage("can't find " + fileName);
        } catch (IOException e) {
            Console.getConsole().printTracingMessage("some thing went wrong while reading in " + fileName);
        } catch (ClassNotFoundException e) {
            Console.getConsole().printTracingMessage("can't convert obj that has been written in " + fileName + "to Account");
        }

        return false;
    }


    public boolean searchInDirByUsername(String username){
        try {
            Path path = Paths.get("src/save");
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for(Path path1 : directoryStream){
                if(path1.endsWith(username + ".bin"))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account searchInDirByUsernameAndPass(String username, String password){
        try {
            Path path = Paths.get("src/save");
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for(Path path1 : directoryStream){
                System.out.println(path1);
                if(path1.endsWith(username + ".bin")){
                    Account account = singleObjectFileReader(username + ".bin");
                    if(account.getPassword().equals(password)) {
                        return account;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * get instance of AccountIO
     * @return instance of AccountIO
     */
    public static AccountIO getAccountIO() {
        if(instance == null)
            instance = new AccountIO();
        return instance;
    }

}
