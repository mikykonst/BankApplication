package ua.spalah.bank.exceptions;

public class ClientNotFoundException extends BankException {
    public ClientNotFoundException(String name) {
        super("Client " + name + " not found");
    }
}
