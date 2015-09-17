package tr.edu.metu.ceng352.model;

import tr.edu.metu.ceng352.model.listener.PurchaseListener;
import tr.edu.metu.ceng352.model.listener.PurchaseTransactionListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Serhat CAN on 29.05.2015.
 */
@Entity
@EntityListeners(value = PurchaseTransactionListener.class)
@Table(name = "purchase_transaction")
public class PurchaseTransaction {

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

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id", nullable = false)
    private Purchase purchase;

    @Column(name="amount", nullable = false, precision = 2)
    private Double amount;

    public PurchaseTransaction(){

    }

    public PurchaseTransaction(User user, Purchase purchase, Double amount){
        this.user = user;
        this.purchase = purchase;
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

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}