package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Path path = FileSystems.getDefault().getPath("Transactions2014.csv");

        String line = "";
        String splitBy = ",";
        List<Account> accountList = new ArrayList<>();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));

            while ((line = bufferedReader.readLine()) != null){

                if (!line.equals("Date,From,To,Narrative,Amount")) {

                    String[] transaction = line.split(splitBy);

                    for (Account account : accountList) {

                        if (transaction[1].equals(account.getName())) {
                            account.addTransaction(transaction[0], transaction[1], transaction[2], transaction[3], Integer.parseInt(transaction[4]));
                        } else {
                            accountList.add(new Account(transaction[1]));
                            account.addTransaction(transaction[0], transaction[1], transaction[2], transaction[3], Integer.parseInt(transaction[4]));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
