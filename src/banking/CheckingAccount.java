package banking;

public class CheckingAccount extends BankAccount {
    private static final double OVERDRAFT_FEE = 35.00;

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            balance -= OVERDRAFT_FEE;
            throw new Exception("Frais de découvert appliqués.");
        }
        super.withdraw(amount);
    }
}

