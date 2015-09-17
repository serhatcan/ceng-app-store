package tr.edu.metu.ceng352.model.dbModel;

import java.util.Date;

/**
 * Created by Serhat CAN on 03.06.2015.
 */
public class TransactionLog {

    private Long id;
    private String type;
    private Double amount;
    private Date timeStamp;

    public TransactionLog(Long id, String type, Double amount, Date timeStamp) {
        this.id = id;
        this.type = type;
        this.timeStamp = timeStamp;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
