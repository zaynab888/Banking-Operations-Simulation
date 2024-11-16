package banking;

public class BusinessAccount extends BankAccount {
    private static final double MAINTENANCE_FEE = 20.00;

    public BusinessAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public void applyMaintenanceFee() {
        balance -= MAINTENANCE_FEE;
    }
}
