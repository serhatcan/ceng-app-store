package tr.edu.metu.ceng352.model;

import tr.edu.metu.ceng352.model.listener.ReviewListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serhat CAN on 29.05.2015.
 */

@Entity
@EntityListeners(ReviewListener.class)
@Table(name = "review")
public class Review  implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id", nullable = false)
    private App app;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "vote", nullable = false)
    private int vote;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public Review() {

    }

    public Review(Purchase purchase, App app, String title, String description, int vote) {
        this.purchase = purchase;
        this.app = app;
        this.title = title;
        this.description = description;
        this.vote = vote;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}