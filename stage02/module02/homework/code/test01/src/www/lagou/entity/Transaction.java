package www.lagou.entity;

import java.io.Serializable;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 4220410722086717411L;
    private long id;
    private String cardid;
    private String tratype;
    private double tramoney;
    private String tradate;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cardid='" + cardid + '\'' +
                ", tratype='" + tratype + '\'' +
                ", tramoney=" + tramoney +
                ", tradate=" + tradate +
                '}';
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }


    public String getTratype() {
        return tratype;
    }

    public void setTratype(String tratype) {
        this.tratype = tratype;
    }


    public double getTramoney() {
        return tramoney;
    }

    public void setTramoney(double tramoney) {
        this.tramoney = tramoney;
    }


    public String getTradate() {
        return tradate;
    }

    public void setTradate(String tradate) {
        this.tradate = tradate;
    }

}
