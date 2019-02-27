package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Path path = FileSystems.getDefault().getPath("Transactions2014.csv");

        String line = "";
        String splitBy = ",";
        List<Account> accountList = new ArrayList<>();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));

            while ((line = bufferedReader.readLine()) != null) {

                if (!line.equals("Date,From,To,Narrative,Amount")) {

                    String[] transaction = line.split(splitBy);

                    int from = indexOfAccountWithName(accountList,transaction[1]);

                    int to = indexOfAccountWithName(accountList, transaction[2]);

                    if (from != -1) {
                        accountList.get(from).addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                        System.out.println(accountList.get(from).getTransactionList().get(0).from);
                    }
                    else {
                        Account account = new Account(transaction[1]);
                        accountList.add(account);
                        account.addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                        System.out.println(account.getTransactionList().get(0).from);
                    }
                    
                    if (to != -1) {
                        accountList.get(to).addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                        System.out.println(accountList.get(to).getTransactionList().get(0).to);
                    }
                    else {
                        Account account = new Account(transaction[2]);
                        accountList.add(account);
                        account.addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));
                        System.out.println(account.getTransactionList().get(0).to);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int indexOfAccountWithName(List<Account> accountList, String name) {
        for (Account account: accountList) {
            if (account.getName().equals(name)){
                return accountList.indexOf(account);
            }
        }
        return -1;
    }
}
