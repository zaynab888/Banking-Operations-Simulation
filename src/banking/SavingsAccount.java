package banking;

public class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }
}

