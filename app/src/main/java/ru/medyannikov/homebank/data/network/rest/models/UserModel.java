package ru.medyannikov.homebank.data.network.rest.models;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 10.03.2016.
 */
public class UserModel {
    @Column(name = "idUser")
    @SerializedName("idUser")
    @Expose
    private Integer idUser;

    @Column(name = "firstName")
    @SerializedName("firstName")
    @Expose
    private String firstName;

    @Column(name = "lastName")
    @SerializedName("lastName")
    @Expose
    private String lastName;

    @Column(name = "thirdName")
    @SerializedName("thirdName")
    @Expose
    private String thirdName;

    @Column(name = "email")
    @SerializedName("email")
    @Expose
    private String email;

    @Column(name = "password")
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("login")
    @Expose
    private String login;

    /**
     *
     * @return
     * The idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     * The idUser
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The thirdName
     */
    public String getThirdName() {
        return thirdName;
    }

    /**
     *
     * @param thirdName
     * The thirdName
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The login
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The login
     */
    public void setLogin(String login) {
        this.login = login;
    }
}
