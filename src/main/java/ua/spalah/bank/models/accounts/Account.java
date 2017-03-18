package ua.spalah.bank.models.accounts;

import org.hibernate.annotations.GenericGenerator;
import ua.spalah.bank.models.type.AccountType;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE")
public abstract class Account {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "ID")
  private long id;
  @Column(name = "BALANCE")
  private double balance;
  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private  AccountType accountType;

  public Account(AccountType accountType) {
    this.accountType = accountType;
  }

  protected Account(double balance, AccountType accountType) {
    this.id = id;
    this.balance = balance;
    this.accountType = accountType;
  }

  protected Account() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  @Override
  public String toString() {
    return "Account <-> balance: " + getBalance();
  }
}
