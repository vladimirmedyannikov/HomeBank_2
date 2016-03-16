package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class Bill extends Model {
    @Column(name = "idBill")
    @SerializedName("idBill")
    @Expose
    private int idBill;
    @Column(name = "idUser")
    @SerializedName("idUser")
    @Expose
    private int idUser;
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "about")
    @SerializedName("about")
    @Expose
    private String about;
    @Column(name = "value")
    @SerializedName("summValue")
    @Expose
    private Double value;
    @Column(name = "dateCreated")
    @SerializedName("date")
    @Expose
    private Date date;

    public Bill() {
        name = "Unknow";
        about = "Thi is a new bill";
        value = 1832.4;
    }

    public Bill(int idBill, int idUser, String name, String about, Double value, Date date) {
        this.idBill = idBill;
        this.idUser = idUser;
        this.name = name;
        this.about = about;
        this.value = value;
        this.date = date;
    }

    public int getIdBill() {

        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
