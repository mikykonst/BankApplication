package ua.spalah.bank;

import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.type.Gender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by user on 16.03.2017.
 */
public class Runner {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BankApplication");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    Client client = new Client("Petya", Gender.MALE, "lalal","0000", "Dnepr");

    entityManager.persist(client);

    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();

  }
}
