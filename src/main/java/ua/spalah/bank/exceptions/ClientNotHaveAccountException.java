package ua.spalah.bank.exceptions;

public class ClientNotHaveAccountException extends BankException{
    public ClientNotHaveAccountException(String clientName) {
        super("Client " + clientName + " doesn't have this account.");
    }
}
