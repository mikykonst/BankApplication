package ua.spalah.bank.dao;

import ua.spalah.bank.models.accounts.Account;

import java.util.List;

public interface AccountDao {
    Account save(long clientId, Account account);

    Account update(Account account);

    Account saveOrUpdate(long clientId, Account account);

    void delete(long id);

    Account find(long id);

    List<Account> findAll();

    List<Account> findByClientId(long clientId);

    Account findActiveAccountByClientName(String clientName);

    Account setActiveAccount(long clientId, long id);
    void deleteByClientId(long clientId);
    Account findActiveAccountByClientId(long clientId);

}
