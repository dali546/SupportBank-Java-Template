package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        List<Account> accountList = createListOfAccounts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("SupportBank Transaction List");
        System.out.println("Select an Option:");
        System.out.println("1. List All");
        System.out.println("2. List Specific User");
        int i = Integer.parseInt(scanner.nextLine());

        if (i == 1) {
            listAll(accountList);
        } else if (i == 2) {
            String name = promptForName(accountList);
            int index = indexOfAccountWithName(accountList, name);
            listSpecificAccount(accountList, index);
        } else {
            System.out.println("Invalid Option");
        }
    }

    private static int indexOfAccountWithName(List<Account> accountList, String name) {
        for (Account account : accountList) {
            if (account.getName().equalsIgnoreCase(name)) {
                return accountList.indexOf(account);
            }
        }
        return -1;
    }

    private static List<Account> createListOfAccounts() {

        Path path = FileSystems.getDefault().getPath("Transactions2014.csv");

        String line = "";
        String splitBy = ",";
        List<Account> accountList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));

            while ((line = bufferedReader.readLine()) != null) {

                if (!line.equals("Date,From,To,Narrative,Amount")) {

                    String[] transaction = line.split(splitBy);

                    int from = indexOfAccountWithName(accountList, transaction[1]);

                    int to = indexOfAccountWithName(accountList, transaction[2]);

                    if (from != -1) {
                        accountList.get(from).addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                    } else {
                        Account account = new Account(transaction[1]);
                        accountList.add(account);
                        account.addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                    }

                    if (to != -1) {
                        accountList.get(to).addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));

                    } else {
                        Account account = new Account(transaction[2]);
                        accountList.add(account);
                        account.addTransaction(
                                transaction[0],
                                transaction[1],
                                transaction[2],
                                transaction[3],
                                Double.parseDouble(transaction[4]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    private static void listAll(List<Account> accountList) {
        for (Account account: accountList) {

            double amountTheyOwe = 0;
            double amountOwed = 0;

            System.out.println("Account name: " + account.getName());

            for (Transaction transaction: account.getTransactionList()) {
                if (transaction.from.equals(account.getName())) {
                    amountTheyOwe += transaction.amount;
                } else if (transaction.to.equals(account.getName())) {
                    amountOwed += transaction.amount;
                }

            }

            System.out.println("Amount Owed: £" + new DecimalFormat("#.00").format(amountOwed));
            System.out.println("Amount They Owe: £" + new DecimalFormat("#.00").format(amountTheyOwe));
            System.out.println("");

        }
    }

    private static String promptForName(List<Account> accountList) {
        Scanner scanner = new Scanner(System.in);

        String name = "";
        int index = 0;

        boolean valid = false;

        while (!valid){
            System.out.println("Enter User Name");
            name = scanner.nextLine();
            index = indexOfAccountWithName(accountList, name);
            if (index!=-1){
                valid = true;
            }
        }

        return name;
    }

    private static void listSpecificAccount(List<Account> accountList, int index) {
        Account person = accountList.get(index);
        System.out.println("Account Name: " + person.getName());
        System.out.println("Total Transactions: " + person.getTransactionList().toArray().length);
        System.out.println("Transaction List");
        for (Transaction transaction : person.getTransactionList()) {
            System.out.println(
                    "Date: " + transaction.date + ", " +
                            "From: " + transaction.from + ", " +
                            "To: " + transaction.to + ", " +
                            "Amount Owed: " + transaction.amount + ", " +
                            "Narrative: " + transaction.narrative);
        }
    }
}
