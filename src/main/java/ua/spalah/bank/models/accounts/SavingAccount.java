package ua.spalah.bank.models.accounts;

import ua.spalah.bank.models.type.AccountType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SAVING")
public class SavingAccount extends Account {

  public SavingAccount(){
    super(AccountType.SAVING);
  }
  public SavingAccount(double balance) {
    super(balance, AccountType.SAVING);
  }
}
