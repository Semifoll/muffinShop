package beans;

import java.util.Date;


public class UserAccount {

	private String cod;
	private String nickName;
	private String firstName;
	private String secondName;
	private Date birthDate;
	private String phoneNumber;
	private String password;

	private String accessRights;

	public UserAccount() {

	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String userName) {
		this.firstName = userName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String userName) {
		this.secondName = userName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date date) {
		this.birthDate = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAccessRights() {
		return this.accessRights;
	}

	public void setAccessRights(String rights) {
		if (rights != null) {// condition of "admin" add
			this.accessRights = rights;
		} else {
			// return Error!; or add "admin"
		}
	}

}