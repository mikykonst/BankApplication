package ua.spalah.bank.exceptions;

public class ClientAlreadyExistsException extends BankException {

    public ClientAlreadyExistsException(String name) {
        super("Client " + name + " already exists");
    }
}
