package tr.edu.metu.ceng352.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serhat CAN on 24.05.2015.
 */

@Entity
@Table( name = "OS_version")
public class OsVersion implements Serializable {

    @Id
    @Column(name = "os_version_id")
    private Long id;

    @Column(name = "os_name", nullable = false)
    private String osName;

    public OsVersion() {

    }

    public OsVersion(Long id, String osName) {
        this.id = id;
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

/*
Create Table OS_version(
os_version_id int not null ,
os_name varchar(30) not null,
Primary key (os_version_id)
)
;
 */