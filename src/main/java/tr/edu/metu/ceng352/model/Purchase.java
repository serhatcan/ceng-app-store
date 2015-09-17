package tr.edu.metu.ceng352.model;

import tr.edu.metu.ceng352.model.listener.PurchaseListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Serhat CAN on 29.05.2015.
 */
@Entity
@EntityListeners(value = PurchaseListener.class)
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private Long id;

    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id", nullable = false)
    private App app;

    public Purchase(){

    }

    public Purchase(User user, App app){
        this.user = user;
        this.app = app;
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

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}

/*

Create table Purchase(
purchase_id int not null AUTO_INCREMENT,
user_id int not null ,
app_id int not null,
time_stamp datetime default null,
primary key (purchase_id),
foreign key (user_id) references Users(user_id),
foreign key (app_id) references App(app_id)
)
;
 */