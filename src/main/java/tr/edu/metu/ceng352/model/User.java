package tr.edu.metu.ceng352.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.edu.metu.ceng352.model.listener.UserListener;

import java.util.Collection;
import java.util.Date;

@Entity
@EntityListeners(value = UserListener.class)
@Table(name = "user")
public class User implements java.io.Serializable, UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@JsonIgnore
	private String password;

	@Column(unique = true, name = "iban", nullable = false, length = 11)
	private String iban;

	@Column(precision = 5)
	private Double money = 0.0;

	@Column(name = "time_stamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	@Column(name = "updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "os_version_id")
	@JoinColumn(name = "os_version_id", nullable = true)
	private OsVersion osVersion = null;

	public User() {

	}

	public User(String firstName, String lastName, String username, String email, String iban, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.iban = iban;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public OsVersion getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(OsVersion osVersion) {
		this.osVersion = osVersion;
	}
}



/*
Create table Users(
user_id int not null AUTO_INCREMENT,
email varchar(20) not null UNIQUE,
username varchar(20) not null UNIQUE,
first_name varchar(20) not null,
last_name varchar(20) not null,
iban char(11) not null UNIQUE,
money decimal(11,5) default 0,
password varchar(20) not null,
time_stamp timestamp default SYSDATE(),
updated_time timestamp default SYSDATE(),
os_version_id int not null,
Primary key (user_id),
Foreign key (os_version_id) references OS_version(os_version_id),
CHECK (CHAR_LENGTH(first_name)>1),
CHECK (CHAR_LENGTH(last_name)>1),
CHECK (CHAR_LENGTH(iban)=11),
CHECK (CHAR_LENGTH(password)>5)
)
;


 */