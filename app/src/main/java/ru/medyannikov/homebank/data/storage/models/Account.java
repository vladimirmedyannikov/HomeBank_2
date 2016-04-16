package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vladimir on 12.03.2016.
 */
@Table(name = "Accounts")
public class Account extends Model implements Serializable {

    @Column(name = "idAccount")
    @SerializedName("idAccount")
    @Expose
    private long idAccount;

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
    private Integer status = 0;

    @Column(name = "dateUpdate")
    @SerializedName("dateUpdate")
    @Expose
    private Date dateUpdate = new Date();

    @Column(name = "phoneNumber")
    @SerializedName("phone")
    @Expose
    private String phone;

    @Column(name = "about")
    @SerializedName("about")
    @Expose
    private String about;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "isAuth")
    @Expose
    private boolean auth;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

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
    public Account() {
        super();
    }

    public Account(Integer idAccount, String firstName, String lastName, String thirdName,
                   String email, String token, String urlImage, String urlImageThumb,
                   String urlVk, Date dateUpdate, String phone, String about) {
        this.idAccount = idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.email = email;
        this.token = token;
        this.urlImage = urlImage;
        this.urlImageThumb = urlImageThumb;
        this.urlVk = urlVk;
        this.dateUpdate = dateUpdate;
        this.phone = phone;
        this.about = about;
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
     * @param idAccount
     * @param urlImageThumb
     * @param firstName
     */
    public Account(Integer idAccount, String firstName, String lastName, String thirdName, String email, String token, String urlImage, String urlImageThumb, String urlVk, Integer status) {
        this.idAccount = idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.email = email;
        this.token = token;
        this.urlImage = urlImage;
        this.urlImageThumb = urlImageThumb;
        this.urlVk = urlVk;
        this.status = status;
    }

    /**
     *
     * @return
     * The idAccount
     */
    public long getIdAccount() {
        return idAccount;
    }

    /**
     *
     * @param idAccount
     * The idAccount
     */
    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
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
     * The urlImage
     */
    public String getUrlImage() {
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
    public String getUrlImageThumb() {
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
    public String getUrlVk() {
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
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName() + " " + getThirdName();
    }

    public void copyParam(Account Account) {
        this.setEmail(Account.getEmail());
        this.setFirstName(Account.getFirstName());
        this.setLastName(Account.getLastName());
        //this.setIdAccount(Account.getIdAccount());
        this.setThirdName(Account.getThirdName());
        this.setUrlImage(Account.getUrlImage());
        this.setUrlImageThumb(Account.getUrlImageThumb());
        this.setUrlVk(Account.getUrlVk());
        this.setEmail(Account.getEmail());
        this.setAbout(Account.getAbout());
        this.setPhone(Account.getPhone());
    }
}
