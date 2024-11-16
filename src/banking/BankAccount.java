package banking;

public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) throws Exception {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new Exception("Solde insuffisant ou montant invalide.");
        }
    }
}
