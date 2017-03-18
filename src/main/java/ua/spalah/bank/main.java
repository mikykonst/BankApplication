package ua.spalah.bank;


import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.models.type.Gender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by user on 12.03.2017.
 */
public class main {
  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("BankApplication");
    EntityManager entityManager = factory.createEntityManager();
    entityManager.getTransaction().begin();


    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();
  }
}
