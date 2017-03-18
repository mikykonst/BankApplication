package ua.spalah.bank.models.accounts;

import ua.spalah.bank.models.type.AccountType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends Account {

  @Column(name = "OVERDRAFT")
  private double overdraft;

  public CheckingAccount() {
    super(AccountType.CHECKING);
  }
  public CheckingAccount(double balance, double overdraft) {
    super(balance, AccountType.CHECKING);
    this.overdraft = overdraft;

  }

  public double getOverdraft() {
    return overdraft;
  }

  public void setOverdraft(double overdraft) {
    this.overdraft = overdraft;
  }
}