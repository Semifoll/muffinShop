package beans;

import java.util.Date;

/**
 * Класс служит для создания объекта данных аккаутна пользователя с параметрами:
 * <b>cod</b> - код аккаунта пользователя ,</br>
 * <b>nickName</b> -  никнейм аккаунта пользователя, </br>
 * <b>firstName</b> -  первое имя(имя пользователя) аккаунта пользователя, </br>
 * <b>secondName</b> -  второе имя(фамилия пользователя) аккаунта пользователя, </br>
 * <b>birthDate</b> -  дата рождения аккаунта пользователя, </br>
 * <b>phoneNumber</b> -  номер телефона аккаунта пользователя, </br>
 * <b>password</b> -  пароль аккаунта пользователя, </br>
 * <b>accessRights</b> -  права доступа аккаунта пользователя. </br>
 * @version 1.0
 * @autor Trusov Anton
 */
public class UserAccount {
	/**
	 * Свойство - код аккаунта пользователя
	 */
	private String cod;
	/**
	 * Свойство - никнейм аккаунта пользователя
	 */
	private String nickName;
	/**
	 * Свойство - первое имя(имя пользователя) аккаунта пользователя
	 */
	private String firstName;
	/**
	 * Свойство - второе имя(фамилия пользователя) аккаунта пользователя
	 */
	private String secondName;
	/**
	 * Свойство - дата рождения аккаунта пользователя
	 */
	private Date birthDate;
	/**
	 * Свойство - номер телефона аккаунта пользователя
	 */
	private String phoneNumber;
	/**
	 * Свойство - пароль аккаунта пользователя
	 */
	private String password;
	/**
	 * Свойство - права доступа аккаунта пользователя
	 */
	private String accessRights;
	/** Создает новый объект роли с заданными значениями
	 * @param cod - код аккаунта пользователя ,
	 * @param nickName -  никнейм аккаунта пользователя,
	 * @param firstName -  первое имя(имя пользователя) аккаунта пользователя,
	 * @param secondName -  второе имя(фамилия пользователя) аккаунта пользователя,
	 * @param birthDate -  дата рождения аккаунта пользователя,
	 * @param phoneNumber -  номер телефона аккаунта пользователя,
	 * @param password -  пароль аккаунта пользователя,
	 * @param accessRights -  права доступа аккаунта пользователя.
	 *
	 */
	public UserAccount(){}
	public UserAccount(String cod, String nickName, String firstName,
					   String secondName, Date birthDate, String phoneNumber,
					   String password, String accessRights) {
		this.cod = cod;
		this.nickName = nickName;
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.accessRights = accessRights;

	}
	/** Функция для получения значения поля {@link UserAccount#cod}
	 * @return Возвращает код аккаунта пользователя.
	 */
	public String getCod() {
		return cod;
	}
	/** Функция для назначения значения поля {@link UserAccount#cod}
	 * @param cod - код аккаунта пользователя.
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}
	/** Функция для получения значения поля {@link UserAccount#nickName}
	 * @return Возвращает никнейм аккаунта пользователя.
	 */
	public String getNickName() {
		return nickName;
	}
	/** Функция для назначения значения поля {@link UserAccount#nickName}
	 * @param nickName - никнейм аккаунта пользователя.
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/** Функция для получения значения поля {@link UserAccount#firstName}
	 * @return Возвращает первое имя аккаунта пользователя.
	 */
	public String getFirstName() {
		return firstName;
	}
	/** Функция для назначения значения поля {@link UserAccount#firstName}
	 * @param userName - первое имя пользователя (имя) аккаунта пользователя.
	 */
	public void setFirstName(String userName) {
		this.firstName = userName;
	}
	/** Функция для получения значения поля {@link UserAccount#secondName}
	 * @return Возвращает второе имя аккаунта пользователя.
	 */
	public String getSecondName() {
		return secondName;
	}
	/** Функция для назначения значения поля {@link UserAccount#secondName}
	 * @param userName - второе имя пользователя (фамилия) аккаунта пользователя.
	 */
	public void setSecondName(String userName) {
		this.secondName = userName;
	}
	/** Функция для получения значения поля {@link UserAccount#birthDate}
	 * @return Возвращает дата рождения аккаунта пользователя.
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/** Функция для назначения значения поля {@link UserAccount#birthDate}
	 * @param date - дата рождения аккаунта пользователя.
	 */
	public void setBirthDate(Date date) {
		this.birthDate = date;
	}
	/** Функция для получения значения поля {@link UserAccount#password}
	 * @return Возвращает пароль аккаунта пользователя.
	 */
	public String getPassword() {
		return password;
	}
	/** Функция для назначения значения поля {@link UserAccount#password}
	 * @param password - пароль аккаунта пользователя.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/** Функция для получения значения поля {@link UserAccount#phoneNumber}
	 * @return Возвращает номер телефона аккаунта пользователя.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/** Функция для назначения значения поля {@link UserAccount#phoneNumber}
	 * @param phoneNumber - номер телефона пользователя (имя) аккаунта пользователя.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/** Функция для получения значения поля {@link UserAccount#accessRights}
	 * @return Возвращает права доступа аккаунта пользователя.
	 */
	public String getAccessRights() {
		return this.accessRights;
	}
	/** Функция для назначения значения поля {@link UserAccount#accessRights}
	 * @param rights - права доступа аккаунта пользователя.
	 */
	public void setAccessRights(String rights) {
		if (rights != null) {// condition of "admin" add
			this.accessRights = rights;
		} else {
			// return Error!; or add "admin"
		}
	}

}