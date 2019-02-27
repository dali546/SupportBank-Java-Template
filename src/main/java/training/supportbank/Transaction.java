package training.supportbank;
import java.util.Date;

public class Transaction {
    Date date;
    Account from,to;
    double amount;
    String narrative;


    public Transaction(Date date, Account from, Account to, double amount, String narrative) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.narrative = narrative;
    }
}
