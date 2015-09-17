package tr.edu.metu.ceng352.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serhat CAN on 29.05.2015.
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    public Category() {

    }

    public Category(String title) {
        this.title = title;
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}

/*
Create table Category(
category_id int not null AUTO_INCREMENT,
title varchar(20) not null UNIQUE,
description varchar(200),
Primary key (category_id)
)
 */