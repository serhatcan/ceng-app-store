package tr.edu.metu.ceng352.model;

import tr.edu.metu.ceng352.model.listener.BankTransactionListener;
import tr.edu.metu.ceng352.model.listener.PurchaseTransactionListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Serhat CAN on 29.05.2015.
 */
@Entity
@EntityListeners(value = BankTransactionListener.class)
@Table(name = "bank_transaction")
public class BankTransaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(unique = true, name = "iban", nullable = false, length = 11)
    private String iban;

    @Column(name="amount", nullable = false, precision = 2)
    private Double amount;

    public BankTransaction(){

    }

    public BankTransaction(User user, String iban, Double amount){
        this.user = user;
        this.iban = iban;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}