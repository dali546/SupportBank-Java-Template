package training.supportbank;
import java.util.Date;

public class Transaction {
    String date;
    String from,to;
    double amount;
    String narrative;

    public Transaction(String date, String from, String to, String narrative, double amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = amount;

    }
}
