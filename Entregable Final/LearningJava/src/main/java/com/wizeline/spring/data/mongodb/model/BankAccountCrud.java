package com.wizeline.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "crud")
public class BankAccountCrud {
  @Id
  private String id;

  private String accountName;
  private String userName;
  private boolean accountBalance;

  public BankAccountCrud() {

  }

  public BankAccountCrud(String accountName, String userName, boolean accountBalance) {
    this.accountName = accountName;
    this.userName = userName;
    this.accountBalance = accountBalance;
  }

  public String getId() {
    return id;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean isPublished() {
    return accountBalance;
  }

  public void setPublished(boolean isAccountBalance) {
    this.accountBalance = isAccountBalance;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", accountName=" + accountName + ", userName=" + userName + ", accountBalance=" + accountBalance + "]";
  }
}
