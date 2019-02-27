package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public Account(String name) {
        this.name = name;
    }

    private String name;

    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void addTransaction(String date, String from, String to, String narrative, double amount){
        transactionList.add(new Transaction(date, from, to, narrative, amount));
    }

    public String getName() {
        return name;
    }

}
