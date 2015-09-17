package tr.edu.metu.ceng352.model;

import tr.edu.metu.ceng352.model.listener.AppListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serhat CAN on 29.05.2015.
 */

@Entity
@EntityListeners(AppListener.class)
@Table(name = "App")
public class App implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "app_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false, precision = 5, length = 10)
    private Double price = 0.0;

    @Column(name = "is_approved")
    private Boolean isApproved = true;

    @Column(name = "download_num")
    private int downloadNum = 0;

    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    //@Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "os_version_id", referencedColumnName = "os_version_id", nullable = true)
    private OsVersion osVersion = null;

    public App(String title, String description, Double price, User user, Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.category = category;
    }

    public App(String title, String description, Double price, User user, Category category, OsVersion osVersion) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.category = category;
        this.osVersion = osVersion;
    }

    public App() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public OsVersion getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(OsVersion osVersion) {
        this.osVersion = osVersion;
    }
}