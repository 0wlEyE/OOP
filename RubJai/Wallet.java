import java.io.*;

public class Wallet implements Serializable {
    private double balance;
    private double income;
    private double expense;

    public Wallet(){}
    public Wallet(double balance, double income, double expense){
        this.balance = balance;
        this.income = income;
        this.expense = expense;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public double getExpense() {
        return expense;
    }
    public void setExpense(double expense) {
        this.expense = expense;
    }
}
