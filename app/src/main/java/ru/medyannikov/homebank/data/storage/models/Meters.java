package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vladimir on 26.03.2016.
 */
@Table(name = "Meters")
public class Meters extends Model {
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "bill")
    @SerializedName("bill")
    @Expose
    private Bill bill;
    @Column(name = "value")
    @SerializedName("value")
    @Expose
    private double value;
    @Column(name = "summ")
    @SerializedName("summ")
    @Expose
    private double summ;
    @Column(name = "date_create")
    @SerializedName("date_create")
    @Expose
    private Date date;
    @Column(name = "about")
    @SerializedName("about")
    @Expose
    private String about;
    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;
    @Column(name = "type_meter")
    @SerializedName("type_meter")
    @Expose
    private TypeMeter typeMeters;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public TypeMeter getTypeMeters() {
        return typeMeters;
    }

    public void setTypeMeters(TypeMeter typeMeters) {
        this.typeMeters = typeMeters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
