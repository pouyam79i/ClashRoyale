package org.gamedevs.clashroyale.model.utils.FileIO;

import org.gamedevs.clashroyale.model.player.Human;

import java.io.*;

/**
 * a class for reading and writing human info as object in to file
 *
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * @version 1.0
 */
public class FileIO {

    /**
     * write (append) new object in file
     *
     * @param fileName fileName
     * @param human    human obj
     */
    public static void singleObjectFileWriter(String fileName, Human human) {
        ObjectOutputStream objectOutputStream = null;
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {

            if (bufferedInputStream.read() != -1) {             //check if its empty or not
                objectOutputStream = new MyObjectOutputStream(fileOutputStream);
            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            objectOutputStream.writeObject(human);
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
    public static Human singleObjectFileReader(String fileName) {
        Human human;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            human = (Human) objectInputStream.readObject();
            return human;
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
    public static Human searchInFile(String fileName, String username, String pass) {
        Human human = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            human = (Human) objectInputStream.readObject();
            if (human.getUsername().equals(username) && ((Human) human).getPassword().equals(pass))
                return human;
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
}
