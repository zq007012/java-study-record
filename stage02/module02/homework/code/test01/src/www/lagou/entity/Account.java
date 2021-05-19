package www.lagou.entity;


import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {

  private static final long serialVersionUID = -5975290009878434972L;
  private long id;
  private String username;
  private String card;
  private double balance;
  private List<Transaction> transactions;

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Account() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getCard() {
    return card;
  }

  public void setCard(String card) {
    this.card = card;
  }


  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

}
