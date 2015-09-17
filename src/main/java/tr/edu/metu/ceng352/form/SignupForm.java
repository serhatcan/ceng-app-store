/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.form;

import org.hibernate.validator.constraints.*;

import tr.edu.metu.ceng352.model.Role;
import tr.edu.metu.ceng352.model.User;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";


	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String firstName;

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String lastName;

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String username;

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String iban;

	//private Boolean isDeveloper;

	public String getUsername() {
		return username;
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

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
/*
	public Boolean getIsDeveloper() {
		return isDeveloper;
	}

	public void setIsDeveloper(Boolean isDeveloper) {
		this.isDeveloper = isDeveloper;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public User createAccount() {
		Role role;
		// TODO: signup as developer will be an option
		/*if(isDeveloper) {
			role = Role.ROLE_DEVELOPER;
		} else {
			role = Role.ROLE_USER;
		}*/
		role = Role.ROLE_USER;
		System.out.println(getFirstName() + " " + getLastName() + " " + getUsername() + " " + getEmail() + " " + getIban() + " " + getPassword() + " " + role.toString());
		return new User(getFirstName(), getLastName(), getUsername(), getEmail(), getIban(), getPassword(), role);
	}
}
