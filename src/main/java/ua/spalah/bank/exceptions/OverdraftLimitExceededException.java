package ua.spalah.bank.exceptions;

public class OverdraftLimitExceededException extends NotEnoughFundsException {
    public OverdraftLimitExceededException(double available) {
        super("You exceed your ovedraft only $ " + available);
    }
}
