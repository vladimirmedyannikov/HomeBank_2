package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vladimir on 12.03.2016.
 */
@Table(name = "Users")
public class UserModel extends Model {
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

    @Column(name = "token")
    @SerializedName("token")
    @Expose
    private String token;

    @Column(name = "login")
    @SerializedName("login")
    @Expose
    private String login;

    @Column(name = "urlImage")
    @SerializedName("urlImage")
    @Expose
    private String urlImage;

    @Column(name = "urlImageThumb")
    @SerializedName("urlImageThumb")
    @Expose
    private String urlImageThumb;

    @Column(name = "urlVk")
    @SerializedName("urlVk")
    @Expose
    private String urlVk;

    @Column(name = "status")
    @SerializedName("status")
    @Expose
    private Integer status;

    @Column(name = "dateUpdate")
    @SerializedName("dateUpdate")
    @Expose
    private Date dateUpdate;

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public UserModel() {
        super();
    }

    /**
     *
     * @param urlVk
     * @param thirdName
     * @param lastName
     * @param urlImage
     * @param status
     * @param token
     * @param email
     * @param idUser
     * @param login
     * @param urlImageThumb
     * @param firstName
     */
    public UserModel(Integer idUser, String firstName, String lastName, String thirdName, String email, String token, String login, String urlImage, String urlImageThumb, String urlVk, Integer status) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.email = email;
        this.token = token;
        this.login = login;
        this.urlImage = urlImage;
        this.urlImageThumb = urlImageThumb;
        this.urlVk = urlVk;
        this.status = status;
    }

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

    /**
     *
     * @return
     * The urlImage
     */
    public Object getUrlImage() {
        return urlImage;
    }

    /**
     *
     * @param urlImage
     * The urlImage
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     *
     * @return
     * The urlImageThumb
     */
    public Object getUrlImageThumb() {
        return urlImageThumb;
    }

    /**
     *
     * @param urlImageThumb
     * The urlImageThumb
     */
    public void setUrlImageThumb(String urlImageThumb) {
        this.urlImageThumb = urlImageThumb;
    }

    /**
     *
     * @return
     * The urlVk
     */
    public Object getUrlVk() {
        return urlVk;
    }

    /**
     *
     * @param urlVk
     * The urlVk
     */
    public void setUrlVk(String urlVk) {
        this.urlVk = urlVk;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }}
