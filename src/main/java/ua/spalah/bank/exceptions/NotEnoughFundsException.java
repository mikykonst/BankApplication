package ua.spalah.bank.exceptions;

public class NotEnoughFundsException extends BankException {

    protected NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException(double available) {
        super("Not enough funds, only $" + available);
    }

}
