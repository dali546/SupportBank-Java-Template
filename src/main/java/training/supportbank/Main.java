package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
    public static void main(String args[]) {
        Path path = FileSystems.getDefault().getPath("Transactions2014.csv");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
