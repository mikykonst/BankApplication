package ua.spalah.bank.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.internal.SessionFactoryImpl;
import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.services.ClientService;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 11.03.2017.
 */
public class ClientDaoHiber implements ClientDao {

  private EntityManagerFactory managerFactory;

  public ClientDaoHiber(EntityManagerFactory managerFactory) {
    this.managerFactory = managerFactory;
  }


  @Override
  public Client save(Client client) {
    EntityManager entityManager = managerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    entityManager.persist(client);

    entityManager.getTransaction().commit();
    client = entityManager.find(Client.class, client.getId());
    entityManager.close();
    return client;
  }

  @SuppressWarnings("Duplicates")
  @Override
  public Client update(Client client) {
    EntityManager entityManager = managerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    AccountDaoHiber accountDaoHiber = new AccountDaoHiber(managerFactory);

    List<Account> accounts = entityManager.createQuery("from Account where CLIENT_ID = :cl_id", Account.class).setParameter("cl_id", client.getId()).getResultList();

//    entityManager.createNativeQuery("DELETE FROM ACCOUNTS WHERE CLIENT_ID = :cl_id").setParameter("cl_id", client.getId()).executeUpdate();
    entityManager.merge(client);

    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();
    entityManager.createQuery("", Account.class).executeUpdate();
    entityManager.getTransaction().commit();
    client = entityManager.find(Client.class, client.getId());
    entityManager.close();
    return client;
  }

  @SuppressWarnings("Duplicates")
  @Override
  public Client saveOrUpdate(Client client) {
    if (client.getId() == 0) {
      save(client);
    } else {
      update(client);
    }
    return client;
  }

  @Override
  public void delete(long clientId) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    Client client = entityManager.find(Client.class, clientId);
    entityManager.remove(client);

    entityManager.getTransaction().commit();
    client = entityManager.find(Client.class, client.getId());
    entityManager.close();
  }

  @Override
  public Client find(long id) {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    Client client = entityManager.find(Client.class, id);

    entityManager.getTransaction().commit();
    client = entityManager.find(Client.class, client.getId());
    entityManager.close();
    return client;
  }

  @Override
  public List<Client> findAll() {
    EntityManager entityManager = managerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    List<Client> list = entityManager.createQuery("from Client", Client.class).getResultList();
    return list;
  }

  @Override
  public Client findByName(String name) throws ClientNotFoundException {
    EntityManager entityManager = managerFactory.createEntityManager();
    Client client = entityManager.createQuery("select cl from Client cl where cl.name=" + name, Client.class).getSingleResult();
    entityManager.close();
    return client;
  }
}