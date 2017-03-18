package ua.spalah.bank.listeners;

import ua.spalah.bank.models.Client;

public interface ClientRegistrationListener {
    void onClientAdded(Client c);
}
