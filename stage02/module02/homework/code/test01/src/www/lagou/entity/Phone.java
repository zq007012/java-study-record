package www.lagou.entity;

import java.io.Serializable;

public class Phone implements Serializable {

  private static final long serialVersionUID = 2991987603201701811L;
  private long id;
  private String pname;
  private double price;
  private java.sql.Date prodate;
  private String color;

  public Phone(){}
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public java.sql.Date getProdate() {
    return prodate;
  }

  public void setProdate(java.sql.Date prodate) {
    this.prodate = prodate;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Phone{" +
            "id=" + id +
            ", pname='" + pname + '\'' +
            ", price=" + price +
            ", prodate=" + prodate +
            ", color='" + color + '\'' +
            '}';
  }
}
