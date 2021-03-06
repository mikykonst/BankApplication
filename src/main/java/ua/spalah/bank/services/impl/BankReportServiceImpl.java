package ua.spalah.bank.services.impl;

import ua.spalah.bank.models.Client;
import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.type.AccountType;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.dao.ClientDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankReportServiceImpl implements BankReportService {

  private ClientDao clientDao;
  private AccountDao accountDao;

  public BankReportServiceImpl(ClientDao clientDao, AccountDao accountDao) {
    this.clientDao = clientDao;
    this.accountDao = accountDao;
  }

  @Override
  public int getNumberOfClients() {
    return clientDao.findAll().size();
  }

  @Override
  public int getNumberOfAccounts() {
    return accountDao.findAll().size();
  }

  @Override
  public double getTotalAccountSum() {
    double totalSum = 0;
    for (Client client : clientDao.findAll()) {
      for (Account account : accountDao.findByClientId(client.getId())) {
        totalSum += account.getBalance();
      }
    }
    return totalSum;
  }

  @Override
  public double getBankCreditSum() {
    double totalCredit = 0;
    for (Client client : clientDao.findAll()) {
      for (Account account : accountDao.findByClientId(client.getId())) {
        if (account.getAccountType().equals(AccountType.CHECKING)) {
          CheckingAccount checkingAccount = (CheckingAccount) account;
          totalCredit += checkingAccount.getOverdraft();
        }
      }
    }
    return totalCredit;
  }

  @Override
  public List<Client> getClientsSortedByName() {
    List<Client> clients = new ArrayList<Client>(clientDao.findAll());
    clients.sort(new Comparator<Client>() {
      @Override
      public int compare(Client client1, Client client2) {
        return client1.getName().compareTo(client2.getName());
      }
    });
    return clients;
    //return new TreeMap<>(bank.getClients());
  }

  @Override
  public Map<String, List<Client>> getClientsByCity() {
    Map<String, List<Client>> clientsByCity = new HashMap<>();
    for (Client client : clientDao.findAll()) {
      if (!clientsByCity.containsKey(client.getCity())) {
        clientsByCity.put(client.getCity(), new ArrayList<>());
      }
      clientsByCity.get(client.getCity()).add(client);
    }
    return clientsByCity;
  }
}
