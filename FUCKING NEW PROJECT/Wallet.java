public class Wallet {
    private double balance;
    private double income;
    private double expense;

    public Wallet(){
        setBalance(0.00);
        setIncome(0.0);
        setExpense(0.0);
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
