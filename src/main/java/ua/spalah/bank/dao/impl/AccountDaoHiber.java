package ua.spalah.bank.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.03.2017.
 */
public class AccountDaoHiber implements AccountDao {

  EntityManagerFactory managerFactory;

  public AccountDaoHiber(EntityManagerFactory managerFactory) {
    this.managerFactory = managerFactory;
  }

  @Override
  public Account save(long clientId, Account account) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(account);
    account = entityManager.find(Account.class, account.getId());
    entityManager.createQuery("UPDATE Account set CLIENT_ID = :clientId where id=:accountId").setParameter("clientId", clientId).setParameter("accountId", account.getId()).executeUpdate();

    entityManager.getTransaction().commit();
    entityManager.close();
    return account;
  }

  @Override
  public Account update(Account account) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    entityManager.merge(account);

    entityManager.getTransaction().commit();
    entityManager.close();

    return account;
  }

  @Override
  public Account saveOrUpdate(long clientId, Account account) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    if (account.getId() == 0) {
      save(clientId, account);
    } else {
      update(account);
    }

    return account;
  }

  @Override
  public void delete(long id) {


  }

  @Override
  public Account find(long id) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Account account = entityManager.find(Account.class, id);

    entityManager.close();
    return account;
  }

  @Override
  public List<Account> findAll() {
    EntityManager entityManager = managerFactory.createEntityManager();
    List<Account> list = entityManager.createQuery("from Account", Account.class).getResultList();
    entityManager.close();
    return list;
  }

  @Override
  public List<Account> findByClientId(long clientId) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Account> accounts = entityManager.createNativeQuery("SELECT * FROM PUBLIC.ACCOUNTS WHERE CLIENT_ID=" + clientId, Account.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return accounts;
  }

  @Override
  public Account findActiveAccountByClientName(String clientName) {
    Account account = null;
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("select cl from Client cl where cl.name=:name");
    query.setParameter("name", clientName);
    Client client = (Client) query.getSingleResult();
    Query query1 = entityManager.createNativeQuery("SELECT active_account_id from PUBLIC .CLIENTS WHERE ID=" + client.getId());
    Object result = query1.getSingleResult();
    if (result != null) {
      long accountId = Long.parseLong(result.toString());
      account = entityManager.find(Account.class, accountId);
    }
    entityManager.getTransaction().commit();
    entityManager.close();
    return account;
  }

  @Override
  public Account setActiveAccount(long clientId, long id) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Client client = entityManager.createQuery("from Client cl where cl.id=" + clientId, Client.class).getSingleResult();
    Account account = entityManager.find(Account.class, id);
    client.setActiveAccount(account);
    entityManager.merge(client);
    entityManager.getTransaction().commit();
    entityManager.close();
    return null;
  }

  @Override
  public void deleteByClientId(long clientId) {

  }

  @Override
  public Account findActiveAccountByClientId(long clientId) {
    Account account = null;
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Client client = entityManager.find(Client.class, clientId);
    Query query = entityManager.createNativeQuery("SELECT active_account_id from PUBLIC .CLIENTS WHERE ID=" + client.getId());
    Object result = query.getSingleResult();
    if (result != null) {
      long accountId = Long.parseLong(result.toString());
      account = entityManager.find(Account.class, accountId);
    }
    entityManager.getTransaction().commit();
    entityManager.close();
    return account;
  }
}
