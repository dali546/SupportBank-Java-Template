package training.supportbank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String name;
    //Transaction transaction;
    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void addTransaction(Date date, Account from, Account to, String narrative, double amount){
        transactionList.add(new Transaction());
    }

    public String getName() {
        return name;
    }

}
